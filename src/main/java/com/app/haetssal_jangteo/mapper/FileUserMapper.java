package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileUserVO;
import com.app.haetssal_jangteo.dto.FileUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FileUserMapper {
    // 유저 프로필 이미지 등록
    public void insert(FileUserVO fileUserVO);

    // 유저 id로 프로필 이미지 조회
    public Optional<FileUserDTO> selectById(Long userId);

    // 유저 id로 프로필 이미지삭제
    public void deleteByUserId(Long userId);
}
