package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.dto.MarketDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MarketMapper {
//    장터 등록
    public void insert(MarketDTO marketDTO);

//    전체 장터들 검색
    public List<MarketVO> selectAll();

//    지역으로 장터들 검색
    public List<MarketVO> selectByRegion(String marketRegion);

//    id 로 장터 검색
    public Optional<MarketVO> selectById(Long id);
}
