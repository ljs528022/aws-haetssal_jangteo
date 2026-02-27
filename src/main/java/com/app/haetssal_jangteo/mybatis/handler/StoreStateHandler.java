package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.common.enumeration.StoreState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(StoreState.class)
public class StoreStateHandler implements TypeHandler<StoreState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, StoreState parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public StoreState getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "pending":
                return StoreState.PENDING;
            case "denied":
                return StoreState.DENIED;
            case "open":
                return StoreState.OPEN;
            case "close":
                return StoreState.CLOSE;
        }
        return null;
    }

    @Override
    public StoreState getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "pending":
                return StoreState.PENDING;
            case "denied":
                return StoreState.DENIED;
            case "open":
                return StoreState.OPEN;
            case "close":
                return StoreState.CLOSE;
        }
        return null;
    }

    @Override
    public StoreState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "pending":
                return StoreState.PENDING;
            case "denied":
                return StoreState.DENIED;
            case "open":
                return StoreState.OPEN;
            case "close":
                return StoreState.CLOSE;
        }
        return null;
    }
}
