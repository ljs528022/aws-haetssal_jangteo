package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminUserDAO {
    private final AdminUserMapper adminUserMapper;

    //    전체 개수
    public int findTotal(Search search) {
        return adminUserMapper.selectTotal(search);
    }

    //    회원 목록 조회
    public List<UserDTO> findAll(Criteria criteria, Search search) {
        return adminUserMapper.selectAll(criteria, search);
    }

    //    전체 회원 조회
    public List<UserDTO> findAllUsers() {
        return adminUserMapper.selectAllUsers();
    }

    //    수정
    public void setUser(UserDTO userDTO) {
        adminUserMapper.update(userDTO);
    }
}
