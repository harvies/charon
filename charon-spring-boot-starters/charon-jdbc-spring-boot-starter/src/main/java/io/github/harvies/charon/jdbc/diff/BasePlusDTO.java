package io.github.harvies.charon.jdbc.diff;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePlusDTO {

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
