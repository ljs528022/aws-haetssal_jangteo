package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        return User.getUser(source);
    }
}
