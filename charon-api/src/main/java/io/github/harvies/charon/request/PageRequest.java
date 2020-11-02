package io.github.harvies.charon.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
public class PageRequest implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * 偏移量
     */
    private int offset = 0;

    /**
     * 每页条数
     */
    private int limit = 20;
}
