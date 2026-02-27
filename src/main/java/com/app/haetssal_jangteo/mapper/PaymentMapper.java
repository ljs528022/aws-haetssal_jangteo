package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {
    // 거래완료인 애들 목록
    public List<PaymentDTO> selectCompleteItemsByUserId(Long userId);

    // 결제?거래?중인 애들 목록
//    public List<PaymentDTO> selectPendingByUserId(Long userId);
}
