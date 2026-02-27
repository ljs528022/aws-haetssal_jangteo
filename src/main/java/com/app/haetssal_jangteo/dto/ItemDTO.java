package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import lombok.*;
import org.apache.tomcat.util.http.fileupload.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private Long itemStoreId;
    private Long itemCategoryId;
    private Long itemSubCategoryId;
    private String itemName;
    private String itemType;
    private String itemPrice;
    private String itemStock;
    private String itemDeliveryFee;
    private String itemContent;
    private State itemState;
    private int itemViewCount;
    private String createdDatetime;
    private String updatedDatetime;
    private String categoryName;

//    카테고리 이름
    private String itemCategoryName;
    private String itemSubCategoryName;

//    상품 가게 정보
    private String storeName;

//    상품 옵션 (저장을 위한 값)
    private List<ItemOptionDTO> itemOptions = new ArrayList<>();
    private String[] optionIdsToDelete;

//    상품 썸네일
    private List<FileItemDTO> itemFiles = new ArrayList<>();
    private String[] fileIdsToDelete;

//    toVO
    public ItemVO toVO() {
        return ItemVO.builder()
                .id(id)
                .itemStoreId(itemStoreId)
                .itemCategoryId(itemCategoryId)
                .itemSubCategoryId(itemSubCategoryId)
                .itemName(itemName)
                .itemType(itemType)
                .itemStock(itemStock)
                .itemPrice(itemPrice)
                .itemDeliveryFee(itemDeliveryFee)
                .itemContent(itemContent)
                .itemState(itemState)
                .itemViewCount(itemViewCount)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
