package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.common.enumeration.Provider;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OAuthVO {
    private Long id;
    private Provider authProvider;
}
