package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.User;
import com.app.haetssal_jangteo.domain.OAuthVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.dto.UserNameDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
//    회원가입이랑 로그인
    public void insert(UserDTO userDTO);

    public void insertOauth(OAuthVO oAuthVO);

    public Optional<UserDTO> selectByEmail(String userEmail);

    public Optional<UserDTO> selectUserForLogin(UserDTO userDTO);

    public void updateVisitCountAndLatestLogin(Long id);

//    모디파이에서 쓸거
    public Optional<UserDTO> selectById(Long id);

    public void updateUpdatedDatetimeForProfile(Long id);

    public UserNameDTO selectName(Long id);

    public void updateName(UserDTO userDTO);

    public void updateIntro(UserDTO userDTO);

    public void updatePassword(UserDTO userDTO);

    public void updateProfile(UserDTO userDTO);
}
