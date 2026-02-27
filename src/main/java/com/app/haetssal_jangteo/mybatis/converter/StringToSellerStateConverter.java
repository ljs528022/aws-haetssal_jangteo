package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.SellerState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSellerStateConverter implements Converter<String, SellerState> {
    @Override
    public SellerState convert(String source){
        return SellerState.getSellerState(source);
    }
}
