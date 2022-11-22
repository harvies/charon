package io.github.harvies.charon.mybatis.diff;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePlusPO {

    public static final String BIZ_ID = "biz_id";
    public static final String DELETED = "deleted";

    private Serializable id;

    /**
     * bizId
     */
    private Long bizId;
    /**
     * deleted
     */
    private Long deleted;
}
