package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.State;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(State.class)
public class StateHandler implements TypeHandler<State> {
    @Override
    public void setParameter(PreparedStatement ps, int i, State parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public State getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "active":
                return State.ACTIVE;
            case "inactive":
                return State.INACTIVE;
        }
        return null;
    }

    @Override
    public State getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "active":
                return State.ACTIVE;
            case "inactive":
                return State.INACTIVE;
        }
        return null;
    }

    @Override
    public State getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "active":
                return State.ACTIVE;
            case "inactive":
                return State.INACTIVE;
        }
        return null;
    }
}
