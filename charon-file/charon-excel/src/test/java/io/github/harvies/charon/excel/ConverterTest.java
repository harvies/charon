package io.github.harvies.charon.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import io.github.harvies.charon.util.FileUtils;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConverterTest {

    public static void main(String[] args) {
        List<Head> list = new ArrayList<>();
        Head head = new Head();
//        head.setA("aaa");
        head.setB(Arrays.asList("ccccccccc", "dddddddd", "ccccccccc", "dddddddd", "ccccccccc", "dddddddd", "ccccccccc", "dddddddd"));
        head.setC(Arrays.asList(111111111, 1222222222, 1222222222, 1222222222, 1222222222, 1222222222, 1222222222));
        list.add(head);
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
//        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setFontHeightInPoints((short) 20);
//        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
//        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//        WriteFont contentWriteFont = new WriteFont();
//        // 字体大小
//        contentWriteFont.setFontHeightInPoints((short) 20);
//        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //单元格不溢出
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.FILL);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        ExcelWriter excelWriter = EasyExcelFactory.write(FileUtils.getCurrentUserHomePath() + "/Downloads/test_write.xlsx")
                .head(Head.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .build();
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetNo(0);
        writeSheet.setSheetName("工作表1");
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
    }

    @Data
    public static class Head {
        //        private String a;
        @ExcelProperty(value = "b", converter = ListStringConverter.class)
        private List<String> b;
        @ExcelProperty(value = "c", converter = ListStringConverter.class)
        private List<Integer> c;
    }
}
