package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    //    전체 회원 조회
    public List<UserDTO> selectAllUsers();

    //   전체 개수
    public int selectTotal(@Param("search") Search search);

    //    목록
    public List<UserDTO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //    수정
    public void update(UserDTO UserDTO);
}
