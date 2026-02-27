package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.User;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(User.class)
public class UserHandler implements TypeHandler<User> {

    @Override
    public void setParameter(PreparedStatement ps, int i, User parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public User getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "normal":
                return User.NORMAL;
            case "seller":
                return User.SELLER;
            case "admin":
                return User.ADMIN;
        }
        return null;
    }

    @Override
    public User getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "normal":
                return User.NORMAL;
            case "seller":
                return User.SELLER;
            case "admin":
                return User.ADMIN;
        }
        return null;
    }

    @Override
    public User getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "normal":
                return User.NORMAL;
            case "seller":
                return User.SELLER;
            case "admin":
                return User.ADMIN;
        }
        return null;
    }
}
