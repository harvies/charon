package io.github.harvies.charon.tests;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.github.harvies.charon.picture.ImageWatermark;
import io.github.harvies.charon.picture.PicUtils;
import io.github.harvies.charon.util.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import net.dongliu.requests.body.Part;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class AAATest {

    @Data
    public static class Head {
        @ExcelProperty(index = 0, value = "门店名称")
        @ColumnWidth(value = 30)
        private String storeName;

        @ExcelProperty(index = 1, value = "纳税人识别号")
        @ColumnWidth(value = 25)
        private String registrationNumber;

        @ExcelProperty(index = 2, value = "省")
        @ColumnWidth(value = 10)
        private String providerId;

        @ExcelProperty(index = 3, value = "市")
        @ColumnWidth(value = 10)
        private String cityId;

        @ExcelProperty(index = 4, value = "区")
        @ColumnWidth(value = 10)
        private String areaId;

        @ExcelProperty(index = 5, value = "详细地址")
        @ColumnWidth(value = 30)
        private String address;

        @ExcelProperty(index = 6, value = "门店经度")
        @ColumnWidth(value = 20)
        private String latitude;

        @ExcelProperty(index = 7, value = "门店纬度")
        @ColumnWidth(value = 20)
        private String dimension;

        @ExcelProperty(index = 8, value = "负责人姓名")
        @ColumnWidth(value = 10)
        private String managerName;

        @ExcelProperty(index = 9, value = "联系固话")
        @ColumnWidth(value = 15)
        private String tel;

        @ExcelProperty(index = 10, value = "对外联系手机号")
        @ColumnWidth(value = 15)
        private String phone;

        @ExcelProperty(index = 11, value = "营业时间")
        @ColumnWidth(value = 10)
        private String businessTime;

        @ExcelProperty(index = 12, value = "门店ID")
        @ColumnWidth(value = 10)
        private String storeId;

        @ExcelProperty(index = 13, value = "门店名称")
        @ColumnWidth(value = 30)
        private String storeName2;

        @ExcelProperty(index = 14, value = "营业执照照片链接")
        @ColumnWidth(value = 80)
        private String businessLicense;

        @ExcelProperty(index = 15, value = "门头图照片链接")
        @ColumnWidth(value = 100)
        private String headPicture;

        @ExcelProperty(index = 16, value = "工位图照片链接")
        @ColumnWidth(value = 100)
        private String workStationPicture;
    }

    @Data
    public static class HeadPicture {
        private String url;
        private String value;
    }


    @Test
    public void test() throws IOException {
        byte[] iconByteArray = FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/icon.png"));
        List<Head> list = EasyExcelFactory.read(FileUtils.getCurrentUserHomePath() + "/Downloads/2020-09-03-17-04-50_EXPORT_XLSX_1429245_302_0.xlsx")
                .sheet(0)
                .head(Head.class)
                .doReadSync();
        System.err.println(list);

        ExcelWriter excelWriter = EasyExcelFactory.write(FileUtils.getCurrentUserHomePath() + "/Downloads/2020-09-03-17-04-50_EXPORT_XLSX_1429245_302_0_已打标.xlsx")
                .head(Head.class)
                .build();
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetNo(0);
        writeSheet.setSheetName("工作表1");
        for (Head head : list) {
            if (list.indexOf(head) == 1) {
                break;
            }
            try {
                log.info("当前处理 门店ID:[{}]", head.getStoreId());
                List<HeadPicture> headPictureList = new ArrayList<>();
                for (HeadPicture headPicture : JsonUtils.parseArray(head.getHeadPicture(), HeadPicture.class)) {
                    if (StringUtils.startsWith(headPicture.getUrl(), "//")) {
                        headPicture.setUrl("http:" + headPicture.getUrl());
                    }
                    byte[] responseBytes = Requests.get(headPicture.getUrl()).timeout(60000).send().readToBytes();
                    ImageWatermark.Param.ParamBuilder paramBuilder = ImageWatermark.Param.builder()
                            .icon(iconByteArray)
                            .source(responseBytes)
                            .degree(null)
                            .imageType("jpeg");
                    byte[] watermarkByteArray = ImageWatermark.markImageBySingleIcon(paramBuilder.build());
                    byte[] compressBytes = PicUtils.compressPicForScale(watermarkByteArray, 500, 0.9, 99999999, 99999999);
//                    FileUtils.writeByteArrayToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/aaaa.jpeg"), compressBytes);
                    String readToText = Requests.post(PropertiesUtils.getDefaultProperty("charon.oss.url")).timeout(60000)
                            .multiPartBody(
                                    Part.file("file", RandomUtils.uuid() + ".jpg", compressBytes))
                            .send().readToText();
                    String data = JsonUtils.parseObject(readToText).getJSONArray("data").getString(0);
                    System.err.println("url:" + data);
                    HeadPicture processedHeadPicture = new HeadPicture();
                    processedHeadPicture.setUrl(data);
                    processedHeadPicture.setValue("");
                    headPictureList.add(processedHeadPicture);
                }
                Head writeHead = new Head();
                BeanCopierUtils.copy(head, writeHead);
                writeHead.setHeadPicture(JsonUtils.toJSONString(headPictureList));

                excelWriter.write(Collections.singletonList(writeHead), writeSheet);

                FileUtils.writeStringToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/处理成功.txt"), JsonUtils.toJSONString(writeHead) + "\r\n", StandardCharsets.UTF_8, true);
            } catch (Exception e) {
                log.info("处理异常 storeId:[{}]", head.getStoreId(), e);
                FileUtils.writeStringToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/处理失败.txt"), JsonUtils.toJSONString(head) + "\r\n", StandardCharsets.UTF_8, true);
            }
        }
        excelWriter.finish();
    }
}
