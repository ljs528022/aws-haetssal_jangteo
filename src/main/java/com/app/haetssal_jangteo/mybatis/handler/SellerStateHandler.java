package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.SellerState;
import com.app.haetssal_jangteo.common.enumeration.User;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SellerState.class)
public class SellerStateHandler implements TypeHandler<SellerState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SellerState parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public SellerState getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "pending":
                return SellerState.PENDING;
            case "approved":
                return SellerState.APPROVED;
            case "denied":
                return SellerState.DENIED;
        }
        return null;
    }

    @Override
    public SellerState getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "pending":
                return SellerState.PENDING;
            case "approved":
                return SellerState.APPROVED;
            case "denied":
                return SellerState.DENIED;
        }
        return null;
    }

    @Override
    public SellerState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "pending":
                return SellerState.PENDING;
            case "approved":
                return SellerState.APPROVED;
            case "denied":
                return SellerState.DENIED;
        }
        return null;
    }
}
