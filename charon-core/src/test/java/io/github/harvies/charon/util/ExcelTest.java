package io.github.harvies.charon.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExcelTest {

    @Data
    public static class Head {
        @ExcelProperty(index = 0, value = "门店ID")
        @ColumnWidth(value = 30)
        private String storeId;
        @ExcelProperty(index = 1, value = "营业执照")
        @ColumnWidth(value = 80)
        private String businessLicense;
        @ExcelProperty(index = 2, value = "头图")
        @ColumnWidth(value = 100)
        private String headPicture;
        @ExcelProperty(index = 3, value = "工位照")
        @ColumnWidth(value = 100)
        private String workStationPicture;

    }

    @Test
    public void test() {
        List<Head> list = EasyExcelFactory.read(FileUtils.getCurrentUserHomePath() + "/Downloads/图片地址.xlsx")
                .sheet(0)
                .head(Head.class)
                .doReadSync();
        System.err.println(list);
    }
}
