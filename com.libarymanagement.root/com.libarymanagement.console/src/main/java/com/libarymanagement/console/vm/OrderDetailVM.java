package com.libarymanagement.console.vm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by lenovo on 2018/5/7.
 */
public class OrderDetailVM {

    private String orderNumber;
    private String memberName;
    private String bookName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDateTime;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getPlanDateTime() {
        return planDateTime;
    }

    public void setPlanDateTime(Date planDateTime) {
        this.planDateTime = planDateTime;
    }
}