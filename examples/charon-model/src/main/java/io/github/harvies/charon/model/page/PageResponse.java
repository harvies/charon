package io.github.harvies.charon.model.page;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class PageResponse<T> {

    private static final long serialVersionUID = 1L;

    private Integer total;
    private Boolean hasPre;
    private Boolean hasNext;
    private Integer current;
    private Integer pages;
    private Integer size;
    private Map<String, Object> extra = new HashMap<>();
    protected List<T> records;

    public PageResponse() {
        this.total = 0;
        this.current = 0;
        this.size = 0;
        this.pages = 0;
        this.hasPre = false;
        this.hasNext = false;
        this.records = new LinkedList<>();
    }
}
