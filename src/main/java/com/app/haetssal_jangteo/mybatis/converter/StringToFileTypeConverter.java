package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.enumeration.StoreState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringToFileTypeConverter implements Converter<String, Filetype> {

    @Override
    public Filetype convert(String source) {
        return Filetype.getFiletype(source);
    }
}
