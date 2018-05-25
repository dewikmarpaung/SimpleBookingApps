package com.pabser.FlightReservation.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import javafx.scene.control.Pagination;

@JsonPropertyOrder({"status", "error", "data", "pagination"})
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response<T> {
    @JsonProperty("status")
    private Integer code;
    @JsonProperty("error")
    private String errorMessage;
    @JsonProperty("data")
    @JsonView(View.Base.class)
    private T data;
    private Pagination pagination;
    private Integer expert_unanswer_count;
    private Integer expert_count;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Integer getExpert_unanswer_count() {
        return expert_unanswer_count;
    }

    public void setExpert_unanswer_count(Integer expert_unanswer_count) {
        this.expert_unanswer_count = expert_unanswer_count;
    }

    public Integer getExpert_count() {
        return expert_count;
    }

    public void setExpert_count(Integer expert_count) {
        this.expert_count = expert_count;
    }
}
