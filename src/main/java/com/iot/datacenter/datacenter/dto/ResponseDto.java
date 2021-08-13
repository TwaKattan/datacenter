package com.iot.datacenter.datacenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private String status;
    private String message;
    private Object data;
    private Object paging;

    public ResponseDto() { }

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String status, String message, Object data, Object paging) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.paging = paging;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public Object getPaging() {
        return paging;
    }
    public void setPaging(Object paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}