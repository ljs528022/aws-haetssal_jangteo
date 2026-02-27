package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.FileUserVO;
import com.app.haetssal_jangteo.dto.FileUserDTO;
import com.app.haetssal_jangteo.mapper.FileUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileUserDAO {
    private final FileUserMapper fileUserMapper;

    // 프필 이미지 등록
    public void saveProfileImage(FileUserVO fileUserVO) {
        fileUserMapper.insert(fileUserVO);
    }

    // 프필 이미지 조회
    public Optional<FileUserDTO> findProfileImageByUserId(Long userId) {
        return fileUserMapper.selectById(userId);
    }

    // 프필 이미지 삭제
    public void deleteProfileImageByUserId(Long id) {
        fileUserMapper.deleteByUserId(id);
    }
}
