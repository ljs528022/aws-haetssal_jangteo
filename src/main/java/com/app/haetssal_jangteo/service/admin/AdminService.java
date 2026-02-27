package com.app.haetssal_jangteo.service.admin;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminService {
    private final AdminItemDAO adminItemDAO;
    private final AdminStoreDAO adminStoreDAO;
    private final AdminUserDAO adminUserDAO;
    private final FileDAO fileDAO;
    private final FileItemDAO fileItemDAO;

    // 상품 목록 조회
    public ItemWithPagingDTO list(int page, Search search) {
        ItemWithPagingDTO itemWithPagingDTO = new ItemWithPagingDTO();
        int total = adminItemDAO.findTotal(search);
        Criteria criteria = new Criteria(page, total);

        List<ItemDTO> items = adminItemDAO.findAll(criteria, search);

        criteria.setHasMore(items.size() > criteria.getRowCount());
        itemWithPagingDTO.setTotal(total);
        itemWithPagingDTO.setCriteria(criteria);

        if (criteria.isHasMore()) {
            items.remove(items.size() - 1);
        }
        itemWithPagingDTO.setItems(items);

        return itemWithPagingDTO;
    }

    // 상품 수정
    public void update(ItemDTO itemDTO, List<MultipartFile> multipartFiles) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        adminItemDAO.setItem(itemDTO.toVO());

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            multipartFiles.forEach(multipartFile -> {
                if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().isEmpty()) {
                    return;
                }

                UUID uuid = UUID.randomUUID();
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileSavedPath(todayPath);
                fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
                fileDTO.setFileType(multipartFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
                fileDAO.save(fileDTO);

                FileItemDTO fileItemDTO = new FileItemDTO();
                fileItemDTO.setId(fileDTO.getId());
                fileItemDTO.setItemId(itemDTO.getId());
                fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);
                fileItemDAO.save(fileItemDTO.toFileItemVO());

                File directory = new File(path);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                try {
                    multipartFile.transferTo(new File(path, fileDTO.getFileName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    // 카테고리 목록 조회
    public List<String> findCategories() {
        return adminItemDAO.findCategories();
    }

    // 가게 목록 조회
    public StoreWithPagingDTO storeList(int page, Search search) {
        StoreWithPagingDTO storeWithPagingDTO = new StoreWithPagingDTO();
        int total = adminStoreDAO.findTotal(search);
        Criteria criteria = new Criteria(page, total);

        List<StoreDTO> stores = adminStoreDAO.findAll(criteria, search);

        criteria.setHasMore(stores.size() > criteria.getRowCount());
        storeWithPagingDTO.setTotal(total);
        storeWithPagingDTO.setCriteria(criteria);

        if (criteria.isHasMore()) {
            stores.remove(stores.size() - 1);
        }
        storeWithPagingDTO.setStores(stores);

        return storeWithPagingDTO;
    }

    // 가게 수정
    public void updateStore(StoreDTO storeDTO) {
        adminStoreDAO.setStore(storeDTO);
    }

    // 가게 지역 목록 조회
    public List<String> findStoreRegions() {
        return adminStoreDAO.findRegions();
    }

    // 회원 목록 조회
    public UserWithPagingDTO userList(int page, Search search) {
        UserWithPagingDTO userWithPagingDTO = new UserWithPagingDTO();
        int total = adminUserDAO.findTotal(search);
        Criteria criteria = new Criteria(page, total);

        List<UserDTO> users = adminUserDAO.findAll(criteria, search);

        criteria.setHasMore(users.size() > criteria.getRowCount());
        userWithPagingDTO.setTotal(total);
        userWithPagingDTO.setCriteria(criteria);

        if (criteria.isHasMore()) {
            users.remove(users.size() - 1);
        }
        userWithPagingDTO.setUsers(users);

        return userWithPagingDTO;
    }

    // 회원 수정
    public void updateUser(UserDTO userDTO) {
        adminUserDAO.setUser(userDTO);
    }

    // 오늘자 경로 생성
    public String getTodayPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}