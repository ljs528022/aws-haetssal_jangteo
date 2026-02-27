package com.app.haetssal_jangteo.mybatis.converter;

import com.app.haetssal_jangteo.common.enumeration.PaymentState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPaymentState implements Converter<String, PaymentState> {
    @Override
    public PaymentState convert(String source) {
        return PaymentState.getPaymentState(source);
    }
}
