package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import com.app.haetssal_jangteo.common.enumeration.Provider;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.enumeration.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class UserVO extends Period {

    private Long id;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private User userType;
    private String userName;
    private String userIntro;
    private int userVisitCount;
    private String userLatestLogin;
    private State userState;

}
