package cn.com.siss.spring.boot.util.base;

import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName PageResponse
 * @Description TODO
 * @Author clare
 * @Date 2019/3/11 10:23
 * @Version 1.0
 */
@ToString(callSuper = true)
public class PageResponse<T> extends Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> records = Collections.emptyList();
    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;
    private boolean searchCount;
    private boolean optimizeCount;
    private String orderByField;
    private boolean isAsc;

    public PageResponse() {
        this.size = 10;
        this.current = 1;
        this.searchCount = true;
        this.optimizeCount = false;
        this.isAsc = true;
    }

    public PageResponse(Integer current, Integer size) {
        this(current, size, true);
    }

    public PageResponse(Integer current, Integer size, String orderByField) {
        this.setOrderByField(orderByField);
    }


    public PageResponse(Integer current, Integer size, boolean searchCount) {
        this.size = 10;
        this.current = 1;
        this.searchCount = true;
        this.optimizeCount = false;
        this.isAsc = true;
        if (current > 1) {
            this.current = current;
        }

        this.size = size;
        this.searchCount = searchCount;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    protected static Integer offsetCurrent(Integer current, Integer size) {
        return current > 0 ? (current - 1) * size : 0;
    }

    public Integer getOffsetCurrent() {
        return offsetCurrent(this.current, this.size);
    }

    public boolean hasPrevious() {
        return this.current > 1;
    }

    public boolean hasNext() {
        return this.current < this.pages;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPages() {
        if (this.size == null || 0 == this.size || this.total == null) {
            return null;
        } else {
            this.pages = this.total / this.size;
            if (this.total % this.size != 0) {
                ++this.pages;
            }

            return this.pages;
        }
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public boolean isSearchCount() {
        return this.searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isOptimizeCount() {
        return this.optimizeCount;
    }

    public void setOptimizeCount(boolean optimizeCount) {
        this.optimizeCount = optimizeCount;
    }

    public String getOrderByField() {
        return this.orderByField;
    }

    public void setOrderByField(String orderByField) {
        if (StringUtils.isNotEmpty(orderByField)) {
            this.orderByField = orderByField;
        }
    }

    public boolean isAsc() {
        return this.isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public String toString() {
        StringBuffer pg = new StringBuffer();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (this.records != null) {
            pg.append("records-size:").append(this.records.size());
        } else {
            pg.append("records is null");
        }

        return pg.append(" }").toString();
    }

}

