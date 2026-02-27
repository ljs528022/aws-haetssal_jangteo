package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.StoreState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringToFileItemTypeConverter implements Converter<String, FileItemType> {

    @Override
    public FileItemType convert(String source) {
        return FileItemType.getFiletype(source);
    }
}
