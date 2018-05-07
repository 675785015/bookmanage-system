package com.libarymanagement.core.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TbOrder {
    private Long id;

    private String ordNumber;

    private Long ordMemberId;

    private Long ordBookId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordBorrowTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordReturnTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordPlanTime;

    private Integer ordStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdNumber() {
        return ordNumber;
    }

    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber == null ? null : ordNumber.trim();
    }

    public Long getOrdMemberId() {
        return ordMemberId;
    }

    public void setOrdMemberId(Long ordMemberId) {
        this.ordMemberId = ordMemberId;
    }

    public Long getOrdBookId() {
        return ordBookId;
    }

    public void setOrdBookId(Long ordBookId) {
        this.ordBookId = ordBookId;
    }

    public Date getOrdBorrowTime() {
        return ordBorrowTime;
    }

    public void setOrdBorrowTime(Date ordBorrowTime) {
        this.ordBorrowTime = ordBorrowTime;
    }

    public Date getOrdReturnTime() {
        return ordReturnTime;
    }

    public void setOrdReturnTime(Date ordReturnTime) {
        this.ordReturnTime = ordReturnTime;
    }

    public Date getOrdPlanTime() {
        return ordPlanTime;
    }

    public void setOrdPlanTime(Date ordPlanTime) {
        this.ordPlanTime = ordPlanTime;
    }

    public Integer getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(Integer ordStatus) {
        this.ordStatus = ordStatus;
    }
}