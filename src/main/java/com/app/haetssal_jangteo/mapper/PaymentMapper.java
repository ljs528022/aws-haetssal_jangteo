package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.PaymentSearch;
import com.app.haetssal_jangteo.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {
    // 거래완료인 애들 목록 (페이지네이션 + 정렬)
    public List<PaymentDTO> selectCompleteItemsByUserId(@Param("userId") Long userId,
                                                        @Param("criteria") Criteria criteria,
                                                        @Param("search") PaymentSearch search);

    // 결제?거래?중인 애들 목록 (페이지네이션 + 정렬)
    public List<PaymentDTO> selectPendingByUserId(@Param("userId") Long userId,
                                                  @Param("criteria") Criteria criteria,
                                                  @Param("search") PaymentSearch search);

    // 거래완료 개수
    public int selectCompleteCountByUserId(Long userId);

    // 결제중 개수
    public int selectPendingCountByUserId(Long userId);

    // 결제 취소
    public void updatePaymentStateCancelled(Long id);

    // 셀러페이지 에서 주문 조회
    public List<PaymentDTO> selectOrdersById(Long userId);

    public Optional<PaymentDTO> selectPaymentById(Long id);
}