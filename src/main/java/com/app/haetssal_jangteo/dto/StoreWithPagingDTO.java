package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
public class StoreWithPagingDTO {
    private List<StoreDTO> stores;
    private Criteria criteria;
    private int total;
}
