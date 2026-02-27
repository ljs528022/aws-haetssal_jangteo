package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.State;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStateConverter implements Converter<String, State> {
    @Override
    public State convert(String source) {
        return State.getState(source);
    }
}
