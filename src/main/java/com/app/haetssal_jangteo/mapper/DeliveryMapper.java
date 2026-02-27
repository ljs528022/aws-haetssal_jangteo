package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.DeliveryVO;
import com.app.haetssal_jangteo.dto.DeliveryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {
    // 배송지 추가
    public void insert(DeliveryDTO deliveryDTO);

    // 배송지 삭제
    public void delete(Long id);

    // 지금화면에서(유저 id로) 모든 배송지 조회
    public List<DeliveryDTO> selectAllByUserId(Long userId);

    // 배송지 수정(이름)인데 안할건데 혹시몰라서 일단씀
//    public void updateName(DeliveryDTO deliveryDTO);
}
