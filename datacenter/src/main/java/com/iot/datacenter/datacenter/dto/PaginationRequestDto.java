package com.iot.datacenter.datacenter.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationRequestDto {

    private Long page;
    private Long limit;
    private String startDate;
    private String endDate;
    private String columnName;
    private String order;
    private List<SearchTextDto> searchTextList;

    public PaginationRequestDto() { }

    public Long getPage() {
        return page;
    }
    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public List<SearchTextDto> getSearchTextList() {
        return searchTextList;
    }
    public void setSearchTextList(List<SearchTextDto> searchTextList) {
        this.searchTextList = searchTextList;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}