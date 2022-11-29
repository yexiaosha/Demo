package com.yhdemo.demo.handler;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.yhdemo.demo.pojo.SexEnum;

/**
 * 性别枚举转换器
 * @author wyh
 * @date 2022/11/29 13:55
 */
public class SexConverterToExcel implements Converter<SexEnum> {


    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public SexEnum convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        if (cellData.toString().equals(1)){
            return SexEnum.MALE;
        }else {
            return SexEnum.FEMALE;
        }
    }

    @Override
    public CellData convertToExcelData(SexEnum sexEnum, ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(sexEnum.getSex());
    }
}
