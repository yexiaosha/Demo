package com.yhdemo.demo.handler;

import com.yhdemo.demo.pojo.SexEnum;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * 性别枚举转换器
 * @author wyh
 * @date 2022/11/28 11:46
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum.class)
public class SexEnumToIntegerHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setInt(1, sexEnum.getCode());
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getInt(s) == 1 ? SexEnum.MALE : SexEnum.FEMALE;
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(i) == 1 ? SexEnum.MALE : SexEnum.FEMALE;
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getInt(i) == 1 ? SexEnum.MALE : SexEnum.FEMALE;
    }
}
