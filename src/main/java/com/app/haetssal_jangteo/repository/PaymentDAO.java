package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.dto.PaymentDTO;
import com.app.haetssal_jangteo.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    public List<PaymentDTO> findCompletesByUserId(Long userId) {
        return paymentMapper.selectCompleteItemsByUserId(userId);
    }

//    public List<PaymentDTO> findPendingsByUserId(Long userId) {
//        return paymentMapper.selectPendingByUserId(userId);
//    }
}
