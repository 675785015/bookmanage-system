package com.libarymanagement.core.requestModel;

/**
 * Created by Lee on 2018/4/25.
 */
public class OrderSearch {

    private String ordNumber;
    private Integer status;
    private String ordBorrowTimeST;
    private String ordBorrowTimeET;

    public String getOrdNumber() {
        return ordNumber;
    }

    public void setOrdNumber(String ordNumber) {
        this.ordNumber = ordNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrdBorrowTimeST() {
        return ordBorrowTimeST;
    }

    public void setOrdBorrowTimeST(String ordBorrowTimeST) {
        this.ordBorrowTimeST = ordBorrowTimeST;
    }

    public String getOrdBorrowTimeET() {
        return ordBorrowTimeET;
    }

    public void setOrdBorrowTimeET(String ordBorrowTimeET) {
        this.ordBorrowTimeET = ordBorrowTimeET;
    }
}
