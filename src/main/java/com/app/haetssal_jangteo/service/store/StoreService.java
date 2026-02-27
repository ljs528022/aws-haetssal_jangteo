package com.app.haetssal_jangteo.service.store;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.exception.FileNotFoundException;
import com.app.haetssal_jangteo.common.exception.StoreNotFoundException;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import com.app.haetssal_jangteo.util.DateUtils;
import jakarta.mail.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class StoreService {
    private final StoreDAO storeDAO;
    private final StoreDetailDAO storeDetailDAO;
    private final ItemDAO itemDAO;
    private final FileDAO fileDAO;
    private final FileItemDAO fileItemDAO;
    private final FileStoreDAO fileStoreDAO;
    private final ReviewDAO reviewDAO;

    // 가게 등록
    public void save(StoreDTO storeDTO, MultipartFile multipartFile) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        FileStoreDTO fileStoreDTO = new FileStoreDTO();

        storeDAO.save(storeDTO);
        fileStoreDTO.setStoreId(storeDTO.getId());
        if(multipartFile.getOriginalFilename().isEmpty()) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileSavedPath(todayPath);
        fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
        fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
        fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
        fileDTO.setFileType(multipartFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
        fileDAO.save(fileDTO);

        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());

        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try {
            multipartFile.transferTo(new File(path, fileDTO.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 가게 정보 수정
    public void update(StoreDTO storeDTO, MultipartFile multipartFile) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        storeDAO.update(storeDTO.toVO());

        FileDTO fileDTO = new FileDTO();
        FileStoreDTO fileStoreDTO = new FileStoreDTO();

        // 새로 받아온 상점 프로필 저장
        fileStoreDTO.setStoreId(storeDTO.getId());
        if(multipartFile.getOriginalFilename().isEmpty()) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileSavedPath(todayPath);
        fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
        fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
        fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
        fileDTO.setFileType(multipartFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
        fileDAO.save(fileDTO);

        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());

        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try {
            multipartFile.transferTo(new File(path, fileDTO.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ----------- 삭제 -----------
        String fileId = storeDTO.getToDeleteFileId();
        if(fileId != null) {
            FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
            File file = new File(rootPath + fileVO.getFileSavedPath(), fileVO.getFileName());
            if(file.exists()) {
                file.delete();
            }
            fileStoreDAO.delete(Long.valueOf(fileId));
            fileDAO.delete(Long.valueOf(fileId));
        };
    }

    // 가게 상세 조회
    public StoreDetailDTO detail(Long id) {
        Optional<StoreDetailDTO> storeDetailDTO = storeDetailDAO.findById(id);

        if(storeDetailDTO.isPresent()) {
            StoreDetailDTO dto = storeDetailDTO.get();

            // item 가져오기 + 썸내일 같이 가져오기
            List<ItemDTO> items = itemDAO.findByStoreId(dto.getId(), null).stream()
                    .map(itemDTO -> {
                        List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(itemDTO.getId(), "thumbnail");
                        if(!thumbnails.isEmpty()) {
                            itemDTO.setItemFiles(thumbnails);
                        }
                        return itemDTO;
                    }).collect(Collectors.toList());

            // 후기 가져오기
            List<ReviewDTO> reviews = reviewDAO.findReviewsByStoreId(dto.getId(), null).stream()
                    .map(reviewDTO -> {
                        List<FileReviewDTO> reviewImages = reviewDAO.findImagesInReview(reviewDTO.getId());
                        if(!reviewImages.isEmpty()) {
                            reviewDTO.setReviewFiles(reviewImages);
                        }
                        reviewDTO.setCreatedDatetime(DateUtils.toRelativeTime(reviewDTO.getCreatedDatetime()));
                        return reviewDTO;
                    }).collect(Collectors.toList());

            // 마지막 로그인 nn전
            String latestLogin = DateUtils.toRelativeTime(dto.getOwnerLatestLogin());
            dto.setOwnerLatestLogin(latestLogin);

            // 상품 중 10개 만 가져오기
            dto.setStoreItems(items.subList(0, Math.min(items.size(), 10)));

            // 상품 개수
            dto.setItemCount(items.size());

            // 후기 중 4개 정도만 가져오기
            dto.setStoreReviews(reviews.subList(0, Math.min(items.size(), 4)));

            // 후기 개수
            dto.setReviewCount(reviews.size());

            return dto;
        } else {
            throw new StoreNotFoundException();
        }
    }

    // 가게 상태 변경
    public void setState(Long id, String state) {
        storeDAO.setState(id, state);
    }

    // 가게 등록 승인
    public void changeIsConfirmed(Long id) {
        storeDAO.changeIsConfirmed(id);
    }

    // 검색으로 가게 조회
    public StoreWithPagingDTO findBySearch(int page, StoreSearch storeSearch) {
        StoreWithPagingDTO storeWithPagingDTO = new StoreWithPagingDTO();

        Criteria criteria = new Criteria(page, storeDAO.findTotal(storeSearch));
        storeWithPagingDTO.setTotal(storeDAO.findTotal(storeSearch));

        System.out.println("현재 criteria : " + criteria);
        System.out.println("조회된 상품 수 : " + storeDAO.findTotal(storeSearch));

        List<StoreDTO> stores = storeDAO.findBySearch(criteria, storeSearch);

        criteria.setHasMore(stores.size() > criteria.getRowCount());
        storeWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            stores.remove(stores.size() - 1);
        }

        stores.forEach(storeDTO -> {
            storeDTO.setCreatedDatetime(DateUtils.toRelativeTime(storeDTO.getCreatedDatetime()));
        });
        storeWithPagingDTO.setStores(stores);

        return storeWithPagingDTO;
    }

    // 검색 값에 따른 가게 수 조회
    public int findTotal(StoreSearch storeSearch) {
        return storeDAO.findTotal(storeSearch);
    }

    // 오늘자 경로 생성
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // toDTO
    public StoreDTO toDTO(StoreVO storeVO) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(storeVO.getId());
        storeDTO.setStoreMarketId(storeVO.getStoreMarketId());
        storeDTO.setStoreOwnerId(storeVO.getStoreOwnerId());
        storeDTO.setStoreCategoryId(storeVO.getStoreCategoryId());
        storeDTO.setStoreName(storeVO.getStoreName());
        storeDTO.setStoreIntro(storeVO.getStoreIntro());
        storeDTO.setStoreAddress(storeVO.getStoreAddress());
        storeDTO.setStoreScore(storeVO.getStoreScore());
        storeDTO.setStoreState(storeVO.getStoreState());
        storeDTO.setStoreIsConfirmed(storeVO.isStoreIsConfirmed());
        storeDTO.setCreatedDatetime(storeVO.getCreatedDatetime());
        storeDTO.setUpdatedDatetime(storeVO.getUpdatedDatetime());

        return storeDTO;
    }
}
