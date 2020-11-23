package io.github.harvies.charon.tests;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import io.github.harvies.charon.picture.ImageWatermark;
import io.github.harvies.charon.picture.PicUtils;
import io.github.harvies.charon.util.FileUtils;
import io.github.harvies.charon.util.JsonUtils;
import io.github.harvies.charon.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class AAATest {

    @Data
    public static class Head {
        @ExcelProperty(index = 0, value = "网点名称")
        @ColumnWidth(value = 30)
        private String storeName;

        @ExcelProperty(index = 1, value = "企业代码类型")
        @ColumnWidth(value = 10)
        private String codeType;

        @ExcelProperty(index = 2, value = "企业代码")
        @ColumnWidth(value = 20)
        private String registrationNumber;

        @ExcelProperty(index = 3, value = "网点类型（一级类目）")
        @ColumnWidth(value = 10)
        private String one;

        @ExcelProperty(index = 4, value = "网点类型（二级类目）")
        @ColumnWidth(value = 10)
        private String two;

        @ExcelProperty(index = 5, value = "网点区域（省）")
        @ColumnWidth(value = 10)
        private String providerId;

        @ExcelProperty(index = 6, value = "网点区域（市）")
        @ColumnWidth(value = 10)
        private String cityId;

        @ExcelProperty(index = 7, value = "网点区域（区）")
        @ColumnWidth(value = 10)
        private String areaId;

        @ExcelProperty(index = 8, value = "网点地址")
        @ColumnWidth(value = 30)
        private String address;

        @ExcelProperty(index = 9, value = "经纬度地图")
        @ColumnWidth(value = 10)
        private String ld;

        @ExcelProperty(index = 10, value = "网点经度")
        @ColumnWidth(value = 20)
        private String latitude;

        @ExcelProperty(index = 11, value = "网点纬度")
        @ColumnWidth(value = 20)
        private String dimension;

        @ExcelProperty(index = 12, value = "联系人")
        @ColumnWidth(value = 10)
        private String managerName;

        @ExcelProperty(index = 13, value = "客服电话")
        @ColumnWidth(value = 15)
        private String tel;

        @ExcelProperty(index = 14, value = "联系人手机号")
        @ColumnWidth(value = 15)
        private String phone;

        @ExcelProperty(index = 15, value = "营业时间")
        @ColumnWidth(value = 10)
        private String businessTime;

        @ExcelProperty(index = 16, value = "网点介绍")
        @ColumnWidth(value = 10)
        private String storeDesc;

        @ExcelProperty(index = 17, value = "第三方网点ID(选填)")
        @ColumnWidth(value = 10)
        private String storeId;
        @ExcelProperty(index = 18, value = "第三方网点名称(选填)")
        @ColumnWidth(value = 30)
        private String storeName3;
        @ExcelProperty(index = 19, value = "第三方网点类型(选填)")
        @ColumnWidth(value = 30)
        private String storeType;

        @ExcelProperty(index = 20, value = "营业执照照片名称")
        @ColumnWidth(value = 10)
        private String businessLicense;

        @ExcelProperty(index = 21, value = "网点图片名称")
        @ColumnWidth(value = 10)
        private String headPicture;

        @ExcelProperty(index = 22, value = "详情图片名称（选填）")
        @ColumnWidth(value = 10)
        private String workStationPicture;

        @ExcelProperty(index = 23, value = "申请资料名称（选填）")
        @ColumnWidth(value = 10)
        private String workStationPicture1;
    }

    @Data
    public static class HeadPicture {
        private String url;
        private String value;
    }


    @Test
    public void test() throws IOException {
        byte[] iconByteArray = FileUtils.readFileToByteArray(new File(FileUtils.getCurrentUserHomePath() + "/icon.png"));
        List<Head> list = EasyExcelFactory.read(FileUtils.getCurrentUserHomePath() + "/Downloads/网点上架批量申请模板11.21.xlsx")
                .sheet(0)
                .head(Head.class)
                .doReadSync();

        List<List<Head>> partition = Lists.partition(list, 33);

        for (int i = 0; i < partition.size(); i++) {
            log.info("当前页数:[{}]", i);
//            if (i == 10) {
//                break;
//            }
            String picPath = FileUtils.getCurrentUserHomePath() + "/Downloads/平安-洗车/" + i + "/";
            FileUtils.forceMkdir(new File(picPath));
            ExcelWriter excelWriter = EasyExcelFactory.write(picPath + "网点入库批量申请模板.xlsx")
                    .head(Head.class)
                    .build();
            WriteSheet writeSheet = new WriteSheet();
            writeSheet.setSheetNo(0);
            writeSheet.setSheetName("Sheet1");
            for (Head head : partition.get(i)) {
                try {
                    log.info("当前处理 门店ID:[{}]", head.getStoreId());
                    List<HeadPicture> headPictures = JsonUtils.parseArray(head.getHeadPicture(), HeadPicture.class);
                    if (CollectionUtils.isEmpty(headPictures)) {
                        continue;
                    }
                    HeadPicture headPicture = headPictures.get(0);
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
                    FileUtils.writeByteArrayToFile(new File(picPath + head.getStoreId() + "门头照.jpg"), compressBytes);
                    head.setHeadPicture(head.getStoreId() + "门头照");

                    if (StringUtils.isNotBlank(head.getWorkStationPicture())) {
                        if (StringUtils.startsWith(head.getWorkStationPicture(), "//")) {
                            head.setWorkStationPicture("http:" + head.getWorkStationPicture());
                        }
                        FileUtils.writeByteArrayToFile(new File(picPath + head.getStoreId() + "工位照.jpg"), Requests.get(head.getWorkStationPicture()).timeout(60000).send().readToBytes());
                        head.setWorkStationPicture(head.getStoreId() + "工位照");
                    }
                    if (StringUtils.isNotBlank(head.getBusinessLicense())) {
                        if (StringUtils.startsWith(head.getBusinessLicense(), "//")) {
                            head.setBusinessLicense("http:" + head.getBusinessLicense());
                        }
                        try {
                            FileUtils.writeByteArrayToFile(new File(picPath + head.getStoreId() + "营业执照.jpg"), Requests.get(head.getBusinessLicense()).timeout(60000).send().readToBytes());
                            head.setBusinessLicense(head.getStoreId() + "营业执照");
                        } catch (Exception e) {
                            head.setBusinessLicense("");
                        }
                    }
                } catch (Exception e) {
                    log.info("处理异常 storeId:[{}]", head.getStoreId(), e);
                }
            }
            excelWriter.write(partition.get(i), writeSheet);
            excelWriter.finish();
        }

    }
}
