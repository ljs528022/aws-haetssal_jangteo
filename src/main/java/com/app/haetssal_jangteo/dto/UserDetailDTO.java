package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.enumeration.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String userEmail;
    private String userPhone;
    private User userType;
    private String userName;
    private String userIntro;
    private int userVisitCount;
    private String userLatestLogin;
    private State userState;
    private String createdDatetime;
    private String updatedDatetime;
    private Provider authProvider;

    // 유저프로필 이미지
    private String fileName;
    private String fileOriginName;
    private String fileSavedPath;

    // 해당 유저가 남긴 상품의 후기들
    private List<ReviewDTO> storeReviews;
}
