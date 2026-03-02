package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.PaymentSearch;
import com.app.haetssal_jangteo.dto.PaymentDTO;
import com.app.haetssal_jangteo.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    public List<PaymentDTO> findCompletesByUserId(Long userId, Criteria criteria, PaymentSearch search) {
        return paymentMapper.selectCompleteItemsByUserId(userId, criteria, search);
    }

    public List<PaymentDTO> findPendingsByUserId(Long userId, Criteria criteria, PaymentSearch search) {
        return paymentMapper.selectPendingByUserId(userId, criteria, search);
    }

    public int findCompleteCountByUserId(Long userId) {
        return paymentMapper.selectCompleteCountByUserId(userId);
    }

    public int findPendingCountByUserId(Long userId) {
        return paymentMapper.selectPendingCountByUserId(userId);
    }

    public void cancelPayment(Long id) {
        paymentMapper.updatePaymentStateCancelled(id);
    }

    public List<PaymentDTO> findOrdersBySellerId(Long userId) {
        return paymentMapper.selectOrdersById(userId);
    }

    public Optional<PaymentDTO> findPaymentById(Long id) {
        return paymentMapper.selectPaymentById(id);
    }
}