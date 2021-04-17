package io.github.harvies.charon.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
public class PageRequest implements Serializable {

    private static long serialVersionUID = 1L;

    private int pageNo = 1;

    private int pageSize = 10;

    public int getPageNo() {
        if (pageNo < 1) {
            return 1;
        }
        return pageNo;
    }
}
