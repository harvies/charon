package io.github.harvies.charon.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.List;
import java.util.stream.Collectors;

public class ListStringConverter implements Converter<List<Object>> {

    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public List<Object> convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public WriteCellData convertToExcelData(List<Object> value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String collect = value.stream().map(String::valueOf).collect(Collectors.joining(","));
        return new WriteCellData(collect);
    }
}
