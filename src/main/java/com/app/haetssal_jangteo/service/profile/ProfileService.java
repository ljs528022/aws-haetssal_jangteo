package com.app.haetssal_jangteo.service.profile;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.domain.UserVO;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.FileDAO;
import com.app.haetssal_jangteo.repository.FileUserDAO;
import com.app.haetssal_jangteo.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor //주입!
@Transactional(rollbackFor = Exception.class)
public class ProfileService {
    //    주입!
    private final UserDAO userDAO;
    private final FileDAO fileDAO;
    private final FileUserDAO fileUserDAO;

//    프로필 뿌리기
    public Optional<UserDTO> getUserProfile(Long id) {
        return userDAO.findUserById(id);
    }

//    프사 가져오고 없으면 기본으로 설정
    public FileUserDTO getProfileImage(Long userId) {
        Optional<FileUserDTO> fileUserDTO = fileUserDAO.findProfileImageByUserId(userId);
        if (fileUserDTO.isEmpty()) {
            fileUserDTO = Optional.of(new FileUserDTO());
            fileUserDTO.get().setFileSavedPath("/images/");
            fileUserDTO.get().setFileName("haetsal-jangteo-logo.svg");
            fileUserDTO.get().setUserId(userId);
        }

        return fileUserDTO.orElse(null);
    }

    public void saveProfileImage(FileUserDTO fileUserDTO, MultipartFile file) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        Optional<FileUserDTO> alreadyProfile = fileUserDAO.findProfileImageByUserId(fileUserDTO.getUserId());

        if (alreadyProfile.isPresent()) {
            fileUserDAO.deleteProfileImageByUserId(fileUserDTO.getUserId());

//            이걸로 파일 다 삭제하니까 일단 주석하고 리뷰이미지 개발 끝나면
//            이미지에 active inactive 주던가 질문하기
//            fileDAO.delete(alreadyProfile.get().getId());
        }

        FileDTO fileDTO = new FileDTO();

        if (file.getOriginalFilename().isEmpty()) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileSavedPath(todayPath);
        fileDTO.setFileSize(String.valueOf(file.getSize()));
        fileDTO.setFileOriginName(file.getOriginalFilename());
        fileDTO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
        fileDTO.setFileType(file.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
        fileDAO.save(fileDTO);

        fileUserDTO.setId(fileDTO.getId());
        fileUserDAO.saveProfileImage(fileUserDTO.toFileUserVO());

        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        try {
            file.transferTo(new File(path, fileDTO.getFileName()));
//            업데이트타임 변경하기
            userDAO.setDatetime(fileUserDTO.getUserId());
            log.info("됏냐");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserNameDTO getUserName(Long id) {
        return userDAO.findUser(id);
    }

    public void setUserName(UserDTO userDTO) {
        userDAO.setName(userDTO);
    }

    public void setUserInfo(UserDTO userDTO) {
        userDAO.updateIntro(userDTO);
    }

//    입력한 password랑 db비교
    public boolean checkPassword(Long userId, String password) {
        return userDAO.findUserById(userId).get().getUserPassword().equals(password);
    }

    public void changePassword(UserDTO userDTO) {
        userDAO.updatePassword(userDTO);
    }

    // 배송지 목록 조회
    public List<DeliveryDTO> getDeliveryList(Long userId) {
        return userDAO.findAllDeliveriesByUserId(userId);
    }

    // 배송지 추가
    public void addDelivery(DeliveryDTO deliveryDTO) {
        userDAO.saveDelivery(deliveryDTO);
    }

    // 배송지 삭제
    public void removeDelivery(Long id) {
        userDAO.deleteDelivery(id);
    }

    // 오늘자 경로 생성
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
