package com.app.haetssal_jangteo.service.item;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.exception.FileNotFoundException;
import com.app.haetssal_jangteo.common.exception.ItemNotFoundException;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import com.app.haetssal_jangteo.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class   ItemService {
    private final ItemDAO itemDAO;
    private final ItemDetailDAO itemDetailDAO;
    private final CategoryDAO categoryDAO;
    private final FileDAO fileDAO;
    private final FileItemDAO fileItemDAO;

    // 상품 등록
    public void save(ItemDTO itemDTO,
                     ArrayList<MultipartFile> itemThumbnails,
                     ArrayList<MultipartFile> itemDescImages,
                     ArrayList<MultipartFile> itemSellerImages,
                     ArrayList<MultipartFile> itemRefundImages) {
        // 이미지 저장용 경로 지정
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        // 임시로 상품 가게 id 등록
        itemDTO.setItemStoreId(2L);

        itemDAO.save(itemDTO);

        // 옵션이 있다면, 저장
        List<ItemOptionDTO> options = itemDTO.getItemOptions();
        if(!options.isEmpty()) {
            itemDTO.getItemOptions().forEach(optionDTO -> {
                // 저장된 상품 id 주입
                optionDTO.setOptionItemId(itemDTO.getId());
                itemDAO.saveOption(optionDTO.toVO());
            });
        } else {
            // 옵션이 없다면 기본 정보로 최소 하나는 저장
            ItemOptionVO defaultOption = ItemOptionVO.builder()
                    .optionItemId(itemDTO.getId())
                    .optionName(itemDTO.getItemName())
                    .optionDetail(itemDTO.getItemContent())
                    .optionPrice(itemDTO.getItemPrice().replace(",", ""))
                    .optionStock(itemDTO.getItemStock())
                    .build();

            itemDAO.saveOption(defaultOption);
        }

        // 받아온 이미지들이 있으면 저장하기
        // 받아온 이미지들에 따라 FileItemType 설정
        Map<List<MultipartFile>, FileItemType> imageMap = Map.of(
                itemThumbnails, FileItemType.THUMBNAIL,
                itemDescImages, FileItemType.DESC,
                itemSellerImages, FileItemType.SELLER_INFO,
                itemRefundImages, FileItemType.REFUND
        );

        // 각 이미지를 forEach로 저장하는 메소드("saveImages")에 입력
        imageMap.forEach((images, fileItemType) -> {
            if(!images.isEmpty()) {
                saveImages(images, itemDTO.getId(), todayPath, path, fileItemType);
            }
        });
    }

    // 상품 상세 정보 불러오기
    public ItemDetailDTO detail(Long id) {
        Optional<ItemDetailDTO> itemDetailDTO = itemDetailDAO.findItemDetailById(id);

        if(itemDetailDTO.isPresent()) {
            ItemDetailDTO dto = itemDetailDTO.get();

            List<ItemOptionDTO> options = itemDAO.findOptionsById(dto.getId())
                    .stream().map((optionVO) -> toOptionDTO(optionVO)).collect(Collectors.toList());
            List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(dto.getId(), "thumbnail").stream().collect(Collectors.toList());

            // 마지막 로그인 nn전
            String latestLogin = DateUtils.toRelativeTime(dto.getOwnerLatestLogin());
            dto.setOwnerLatestLogin(latestLogin);

            dto.setItemOptions(options);
            dto.setItemThumbnails(thumbnails);

            // 조회수 증가
            itemDAO.increaseViewCount(dto.getId());

            return dto;
        } else {
            throw new ItemNotFoundException();
        }
    }

    // 상품 id과 같은 카테고리의 상품들 불러오기
    public List<ItemDTO> getSameCategoryItems(Long id) {
        ItemVO currentItem = itemDAO.findById(id).orElseThrow(ItemNotFoundException::new);

        List<ItemDTO> sameCategoryItems = itemDAO.findSameCategoryItems(
                currentItem.getItemCategoryId(),
                currentItem.getItemSubCategoryId(),
                currentItem.getId()
        );

        // 가져온 각 상품들의 썸네일을 가져와 저장
        sameCategoryItems.stream().forEach((item) -> {
            item.setItemFiles(fileItemDAO.findImagesByIdAndFileItemType(item.getId(), "thumbnail"));
        });

        return sameCategoryItems;
    }

    // id를 받아와서 해당 상품이 있으면 image return
    public ItemDescImageDTO getItemDescImages(Long id) {
        if(itemDAO.findById(id).isPresent()) {
            ItemDescImageDTO imageDTO = new ItemDescImageDTO();
            imageDTO.setId(id);
            imageDTO.setItemDescImages(fileItemDAO.findImagesByIdAndFileItemType(id, "desc"));
            imageDTO.setItemSellerImages(fileItemDAO.findImagesByIdAndFileItemType(id, "seller-info"));
            imageDTO.setItemRefundImages(fileItemDAO.findImagesByIdAndFileItemType(id, "refund"));

            return imageDTO;
        } else {
            throw new ItemNotFoundException();
        }
    }

    // 가게 id로 상품 조회(무한 스크롤)
    public ItemWithPagingDTO getItemsForDetail(Long storeId, int page) {
        ItemWithPagingDTO itemWithPagingDTO = new ItemWithPagingDTO();
        int total = itemDAO.findTotal(storeId);

        Criteria criteria = new Criteria(page, total);
        itemWithPagingDTO.setTotal(total);

        System.out.println("현재 criteria : " + criteria);
        System.out.println("조회된 상품 수 : " + total);

        List<ItemDTO> storeItems = itemDAO.findByStoreId(storeId, criteria).stream()
                .map(itemDTO -> {
                    List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(itemDTO.getId(), "thumbnail").stream().collect(Collectors.toList());
                    if(!thumbnails.isEmpty()) {
                        itemDTO.setItemFiles(thumbnails);
                    }
                    return itemDTO;
                }).collect(Collectors.toList());

        criteria.setHasMore(storeItems.size() > criteria.getRowCount());
        itemWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            storeItems.remove(storeItems.size() - 1);
        }

        storeItems.forEach(itemDTO -> {
            itemDTO.setCreatedDatetime(DateUtils.toRelativeTime(itemDTO.getCreatedDatetime()));
        });
        itemWithPagingDTO.setItems(storeItems);

        return itemWithPagingDTO;
    }

    // 상품 수정
    public void update(ItemDTO itemDTO,
                       ArrayList<MultipartFile> itemThumbnails,
                       ArrayList<MultipartFile> itemDescImages,
                       ArrayList<MultipartFile> itemSellerImages,
                       ArrayList<MultipartFile> itemRefundImages) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        itemDAO.update(itemDTO.toVO());

        FileDTO fileDTO = new FileDTO();
        FileItemDTO fileItemDTO = new FileItemDTO();

        // 새로 받아온 옵션 저장
        itemDTO.getItemOptions().forEach((optionDTO) -> {
            optionDTO.setOptionItemId(itemDTO.getId());
            itemDAO.saveOption(optionDTO.toVO());
        });

        Map<List<MultipartFile>, FileItemType> imageMap = Map.of(
                itemThumbnails, FileItemType.THUMBNAIL,
                itemDescImages, FileItemType.DESC,
                itemSellerImages, FileItemType.SELLER_INFO,
                itemRefundImages, FileItemType.REFUND
        );

        imageMap.forEach((images, fileItemType) -> {
            if(!images.isEmpty()) {
                saveImages(images, itemDTO.getId(), todayPath, path, fileItemType);
            }
        });

        // ------------- 삭제 -------------
        // 옵션
        if(itemDTO.getOptionIdsToDelete() != null) {
            Arrays.stream(itemDTO.getOptionIdsToDelete()).forEach((optionId) -> {
                itemDAO.deleteOption(Long.valueOf(optionId));
            });
        }

        // 파일(이미지)
        if(itemDTO.getFileIdsToDelete() != null) {
            Arrays.stream(itemDTO.getFileIdsToDelete()).forEach((fileId) -> {
                FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
                File file = new File(rootPath + fileVO.getFileSavedPath(), fileVO.getFileName());
                if(file.exists()) {
                    file.delete();
                }
                fileItemDAO.delete(Long.valueOf(fileId));
                fileDAO.delete(Long.valueOf(fileId));
            });
        }
    }

//    상품 개수 조회
    public int findTotal(Long id) {
        return itemDAO.findTotal(id);
    }

//    삭제
    public void delete(Long id) {
        // 상품 id로 옵션 삭제
        itemDAO.deleteOptionByItemId(id);
        // 실제 파일 삭제
        fileItemDAO.findImagesById(id).forEach(fileItemDTO -> {
            File file = new File("C:/file/" + fileItemDTO.getFileSavedPath(), fileItemDTO.getFileName());
            if(file.exists()) {
                file.delete();
            }

            // 파일 id 가져와서 서버의 데이터 삭제
            Long fileId = fileItemDTO.getId();
            fileItemDAO.delete(fileId);
            fileDAO.delete(fileId);
        });

    }

    // 오늘자 경로 생성
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 이미지 저장 로직
    private void saveImages(List<MultipartFile> images, Long itemId, String todayPath, String path, FileItemType fileItemType) {
        images.forEach(image -> {
            if(image.getOriginalFilename().isEmpty()) {
                return;
            }

            UUID uuid = UUID.randomUUID();
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileType(image.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
            fileDTO.setFileName(uuid.toString() + "_" + image.getOriginalFilename());
            fileDTO.setFileOriginName(image.getOriginalFilename());
            fileDTO.setFileSavedPath(todayPath);
            fileDTO.setFileSize(String.valueOf(image.getSize()));
            fileDAO.save(fileDTO);

            FileItemDTO fileItemDTO = new FileItemDTO();
            fileItemDTO.setId(fileDTO.getId());
            fileItemDTO.setItemId(itemId);
            fileItemDTO.setFileItemType(fileItemType);
            fileItemDAO.save(fileItemDTO.toFileItemVO());

            File directory = new File(path);
            if(!directory.exists()) {
                directory.mkdirs();
            }

            try {
                image.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // toItemDTO
    public ItemDTO toItemDTO (ItemVO itemVO) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemVO.getId());
        itemDTO.setItemStoreId(itemVO.getItemStoreId());
        itemDTO.setItemCategoryId(itemVO.getItemCategoryId());
        itemDTO.setItemSubCategoryId(itemVO.getItemSubCategoryId());
        itemDTO.setItemName(itemVO.getItemName());
        itemDTO.setItemType(itemVO.getItemType());
        itemDTO.setItemPrice(itemVO.getItemPrice());
        itemDTO.setItemStock(itemVO.getItemStock());
        itemDTO.setItemDeliveryFee(itemVO.getItemDeliveryFee());
        itemDTO.setItemContent(itemVO.getItemContent());
        itemDTO.setItemState(itemVO.getItemState());
        itemDTO.setItemViewCount(itemDTO.getItemViewCount());
        itemDTO.setCreatedDatetime(itemVO.getCreatedDatetime());
        itemDTO.setUpdatedDatetime(itemVO.getUpdatedDatetime());

        return itemDTO;
    }

    // toOptionDTO
    public ItemOptionDTO toOptionDTO (ItemOptionVO option) {
        ItemOptionDTO itemOptionDTO = new ItemOptionDTO();
        itemOptionDTO.setId(option.getId());
        itemOptionDTO.setOptionItemId(option.getOptionItemId());
        itemOptionDTO.setOptionName(option.getOptionName());
        itemOptionDTO.setOptionDetail(option.getOptionDetail());
        itemOptionDTO.setOptionPrice(option.getOptionPrice());
        itemOptionDTO.setOptionStock(option.getOptionStock());

        return itemOptionDTO;
    }
}
