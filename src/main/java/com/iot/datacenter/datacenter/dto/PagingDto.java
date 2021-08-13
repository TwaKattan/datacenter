package com.iot.datacenter.datacenter.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingDto implements Serializable {

    private Long totalRecord;
    private Long pageSize;
    private Long currentPage;
    private String columnName;
    private String order;

    public PagingDto() {}

    public PagingDto(Long totalRecord, Long pageSize, Long currentPage) {
        this.totalRecord = totalRecord;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public PagingDto(Long totalRecord, Long pageSize, Long currentPage, String columnName, String order) {
        this.totalRecord = totalRecord;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.columnName = columnName;
        this.order = order;
    }

    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Long getPageSize() {
        return pageSize;
    }
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}