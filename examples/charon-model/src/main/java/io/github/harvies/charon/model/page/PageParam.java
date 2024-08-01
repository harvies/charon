package io.github.harvies.charon.model.page;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class PageParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean openPage;
    private Integer size;
    private Integer start;
    private Integer current;
    private final Integer DEFAULT_CURRENT;
    private final Integer DEFAULT_SIZE;

    public PageParam() {
        this.openPage = false;
        this.DEFAULT_CURRENT = 1;
        this.DEFAULT_SIZE = 10;
        this.current = this.DEFAULT_CURRENT;
        this.size = this.DEFAULT_SIZE;
    }

    public PageParam(Integer current, Integer size, Boolean openPage) {
        this(current, size);
        this.openPage = openPage;
    }

    public PageParam(Integer current, Integer size, Boolean openPage, Integer start) {
        this(current, size, start);
        this.openPage = openPage;
    }

    public PageParam(Integer current, Integer size) {
        this.openPage = false;
        this.DEFAULT_CURRENT = 1;
        this.DEFAULT_SIZE = 10;
        this.setCurrent(current);
        this.setSize(size);
    }

    public PageParam(Integer current, Integer size, Integer start) {
        this.openPage = false;
        this.DEFAULT_CURRENT = 1;
        this.DEFAULT_SIZE = 10;
        this.setCurrent(current);
        this.setSize(size);
        this.setStart(start);
    }

    public Boolean getOpenPage() {
        return this.openPage;
    }

    public void setOpenPage(Boolean openPage) {
        this.openPage = openPage;
    }

    public static long getSerialVersionUID() {
        return 1L;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size < 1 ? this.DEFAULT_SIZE : size;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setCurrent(Integer current) {
        this.current = current <= 0 ? this.DEFAULT_CURRENT : current;
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
