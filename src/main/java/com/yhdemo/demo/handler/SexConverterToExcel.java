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
    public Class<SexEnum> supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public SexEnum convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) {

        if (cellData.getDataFormat().equals(SexEnum.MALE.getCode()) || SexEnum.MALE.getSex()
                .equals(cellData.toString())) {
            return SexEnum.MALE;
        } else if (cellData.getDataFormat().equals(SexEnum.FEMALE.getCode()) || SexEnum.FEMALE.getSex()
                .equals(cellData.toString())) {
            return SexEnum.FEMALE;
        }
        return null;
    }

    @Override
    public CellData<SexEnum> convertToExcelData(SexEnum sexEnum, ExcelContentProperty excelContentProperty,
            GlobalConfiguration globalConfiguration) {
        return new CellData<>(sexEnum.getSex());
    }
}
