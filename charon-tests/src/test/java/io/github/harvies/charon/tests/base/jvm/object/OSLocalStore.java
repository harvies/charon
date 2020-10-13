package io.github.harvies.charon.tests.base.jvm.object;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OSLocalStore implements Serializable {
    private static final long serialVersionUID = 6420805530423980319L;

    private Long storeId;

    private Long nczStoreId;

    private Long sellerId;

    private String storeName;

    private String picUrl;

    private String address;

    private Double latitude;

    private Double longitude;

    private Integer servicedNum;

    private Double score;

    private Integer scoreNum;

    private String businessTime;

    private List<Integer> storeTags;

    private List<Integer> tags;

    private Integer storeType;

    private List<Long> itemCategoryIds;

    private List<Long> itemTagList;

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private Long districtId;

    private String districtName;

    private Long modifyVersion;

    private Integer status;

    private Long createVersion;

    private Double sortDistance;

    private List<String> rankTags;
}