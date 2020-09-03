package io.github.harvies.charon.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.write.metadata.WriteSheet;
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

    @Data
    public static class HeadPicture {
        private String url;
        private String value;
    }


    @Test
    public void test() throws IOException {
        byte[] iconByteArray = FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/icon.png"));
        List<Head> list = EasyExcelFactory.read(FileUtils.getCurrentUserHomePath() + "/Downloads/图片地址.xlsx")
                .sheet(0)
                .head(Head.class)
                .doReadSync();
        System.err.println(list);

        ExcelWriter excelWriter = EasyExcelFactory.write(FileUtils.getCurrentUserHomePath() + "/Downloads/图片地址_已打标.xlsx")
                .head(Head.class)
                .build();
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetNo(0);
        writeSheet.setSheetName("工作表1");
        for (Head head : list) {
            try {
                log.info("当前处理 门店ID:[{}]", head.getStoreId());
                List<HeadPicture> headPictureList = new ArrayList<>();
                for (HeadPicture headPicture : JsonUtils.parseArray(head.getHeadPicture(), HeadPicture.class)) {
                    if (StringUtils.startsWith(headPicture.getUrl(), "//")) {
                        headPicture.setUrl("http:" + headPicture.getUrl());
                    }
                    byte[] responseBytes = Requests.get(headPicture.getUrl()).send().readToBytes();
                    ImageWatermark.Param.ParamBuilder paramBuilder = ImageWatermark.Param.builder()
                            .icon(iconByteArray)
                            .source(responseBytes)
                            .degree(null)
                            .imageType("jpeg");
                    byte[] watermarkByteArray = ImageWatermark.markImageBySingleIcon(paramBuilder.build());
                    byte[] compressBytes = PicUtils.compressPicForScale(watermarkByteArray, 500, 0.9, 99999999, 99999999);
                    FileUtils.writeByteArrayToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/aaaa.jpeg"), compressBytes);
                    String readToText = Requests.post(PropertiesUtils.getDefaultProperty("charon.oss.url"))
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
                writeHead.setBusinessLicense(head.getBusinessLicense());
                writeHead.setStoreId(head.getStoreId());
                writeHead.setWorkStationPicture(head.getWorkStationPicture());
                writeHead.setHeadPicture(JsonUtils.toJSONString(headPictureList));
                excelWriter.write(Collections.singletonList(writeHead), writeSheet);
            } catch (Exception e) {
                log.info("处理异常 storeId:[{}]", head.getStoreId(), e);
                FileUtils.writeStringToFile(new File(FileUtils.getCurrentUserHomePath() + "/Downloads/处理失败.txt"), head.getStoreId(), StandardCharsets.UTF_8);
            }
        }
        excelWriter.finish();
    }
}
