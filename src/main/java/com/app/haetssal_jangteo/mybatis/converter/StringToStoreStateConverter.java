package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.enumeration.StoreState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringToStoreStateConverter implements Converter<String, StoreState> {

    @Override
    public StoreState convert(String source) {
        return StoreState.getStoreState(source);
    }
}
