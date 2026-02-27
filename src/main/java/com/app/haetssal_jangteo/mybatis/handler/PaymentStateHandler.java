package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.PaymentState;
import com.app.haetssal_jangteo.common.enumeration.User;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PaymentState.class)
public class PaymentStateHandler implements TypeHandler<PaymentState> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PaymentState parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public PaymentState getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "pending":
                return PaymentState.PENDING;
            case "shipping":
                return PaymentState.SHIPPING;
            case "complete":
                return PaymentState.COMPLETE;
            case "cancelled":
                return PaymentState.CANCELLED;
        }
        return null;
    }

    @Override
    public PaymentState getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "pending":
                return PaymentState.PENDING;
            case "shipping":
                return PaymentState.SHIPPING;
            case "complete":
                return PaymentState.COMPLETE;
            case "cancelled":
                return PaymentState.CANCELLED;
        }
        return null;
    }

    @Override
    public PaymentState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "pending":
                return PaymentState.PENDING;
            case "shipping":
                return PaymentState.SHIPPING;
            case "complete":
                return PaymentState.COMPLETE;
            case "cancelled":
                return PaymentState.CANCELLED;
        }
        return null;
    }
}
