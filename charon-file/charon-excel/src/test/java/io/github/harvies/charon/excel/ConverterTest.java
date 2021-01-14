package io.github.harvies.charon.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.write.metadata.WriteSheet;
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
        ExcelWriter excelWriter = EasyExcelFactory.write(FileUtils.getCurrentUserHomePath() + "/Downloads/test_write.xlsx")
                .head(Head.class)
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
