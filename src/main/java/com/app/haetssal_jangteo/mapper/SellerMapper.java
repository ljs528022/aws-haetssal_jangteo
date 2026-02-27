package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.OAuthVO;
import com.app.haetssal_jangteo.dto.SellerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SellerMapper {
//    셀러가입
    public void insert(SellerDTO sellerDTO);

}
