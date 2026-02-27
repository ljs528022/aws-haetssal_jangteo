package com.app.haetssal_jangteo.mybatis.handler;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.State;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(FileItemType.class)
public class FileItemHandler implements TypeHandler<FileItemType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, FileItemType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public FileItemType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "thumbnail":
                return FileItemType.THUMBNAIL;
            case "desc":
                return FileItemType.DESC;
            case "seller-info":
                return FileItemType.SELLER_INFO;
            case "refund":
                return FileItemType.REFUND;
        }
        return null;
    }

    @Override
    public FileItemType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "thumbnail":
                return FileItemType.THUMBNAIL;
            case "desc":
                return FileItemType.DESC;
            case "seller-info":
                return FileItemType.SELLER_INFO;
            case "refund":
                return FileItemType.REFUND;
        }
        return null;
    }

    @Override
    public FileItemType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "thumbnail":
                return FileItemType.THUMBNAIL;
            case "desc":
                return FileItemType.DESC;
            case "seller-info":
                return FileItemType.SELLER_INFO;
            case "refund":
                return FileItemType.REFUND;
        }
        return null;
    }
}
