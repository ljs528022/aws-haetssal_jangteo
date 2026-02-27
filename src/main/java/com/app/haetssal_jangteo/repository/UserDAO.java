package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.OAuthVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.DeliveryDTO;
import com.app.haetssal_jangteo.dto.SellerDTO;
import com.app.haetssal_jangteo.dto.UserDTO;
import com.app.haetssal_jangteo.dto.UserNameDTO;
import com.app.haetssal_jangteo.mapper.DeliveryMapper;
import com.app.haetssal_jangteo.mapper.SellerMapper;
import com.app.haetssal_jangteo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
//    주입!
    private final UserMapper userMapper;
    private final SellerMapper sellerMapper;
    private final DeliveryMapper deliveryMapper;


//==========================회원가입, 로그인============================
    //    이메일 검사
    public Optional<UserDTO> findByUserEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }

//    회원가입
    public void save(UserDTO userDTO){
        userMapper.insert(userDTO);
    }

    public void saveSeller(SellerDTO sellerDTO) {
        sellerMapper.insert(sellerDTO);
    }
//    oauth
    public void saveOAuth(OAuthVO oAuthVO){
        userMapper.insertOauth(oAuthVO);
    }

    // 로그인
    public Optional<UserDTO> findForLogin(UserDTO userDTO) {
        return userMapper.selectUserForLogin(userDTO);
    }
//    로그인했을때 정보업뎃(로그인횟수랑 최근로그인언젠지)
    public void setUserVisit(Long id) {
        userMapper.updateVisitCountAndLatestLogin(id);
    }

//==========================프로필수정============================

    // 유저id로 조회하기 - 프로필편집에서 쓸거임
    public Optional<UserDTO> findUserById(Long id) {
        return userMapper.selectById(id);
    }

//    프로필이미지 변경할때 사용하는 업데이트타임
    public void setDatetime(Long id) {
        userMapper.updateUpdatedDatetimeForProfile(id);
    }

    // 배송지 추가
    public void saveDelivery(DeliveryDTO deliveryDTO) {
        deliveryMapper.insert(deliveryDTO);
    }

    // 배송지 삭제
    public void deleteDelivery(Long id) {
        deliveryMapper.delete(id);
    }

    // 배송지 목록 조회
    public List<DeliveryDTO> findAllDeliveriesByUserId(Long userId) {
        return deliveryMapper.selectAllByUserId(userId);
    }

    // 배송지 이름 수정
//    public void setDeliveryName(DeliveryDTO deliveryDTO) {
//        deliveryMapper.updateName(deliveryDTO);
//    }

    // 이름 조회
    public UserNameDTO findUser(Long id) {
        return userMapper.selectName(id);
    }

    // 이름?닉네임? 수정
    public void setName(UserDTO userDTO) {
        userMapper.updateName(userDTO);
    }

    // 유저소개 수정
    public void updateIntro(UserDTO userDTO) {
        userMapper.updateIntro(userDTO);
    }

    // 비밀번호 수정
    public void updatePassword(UserDTO userDTO) {
        userMapper.updatePassword(userDTO);
    }

}
