package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.domain.UserVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class UserNameDTO {
    private Long id;
    private String userName;

    public UserVO toUserVO() {
        return UserVO.builder()
                .id(id)
                .userName(userName)
                .build();
    }
}
