package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.enumeration.State;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Filetype.class)
public class FileTypeHandler implements TypeHandler<Filetype> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Filetype parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Filetype getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "image":
                return Filetype.IMAGE;
            case "document":
                return Filetype.DOCUMENT;
        }
        return null;
    }

    @Override
    public Filetype getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "image":
                return Filetype.IMAGE;
            case "document":
                return Filetype.DOCUMENT;
        }
        return null;
    }

    @Override
    public Filetype getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "image":
                return Filetype.IMAGE;
            case "document":
                return Filetype.DOCUMENT;
        }
        return null;
    }
}
