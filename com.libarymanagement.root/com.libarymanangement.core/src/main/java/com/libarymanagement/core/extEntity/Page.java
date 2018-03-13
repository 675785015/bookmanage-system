package com.libarymanagement.core.extEntity;

import java.util.List;

/**
 * Created by Lee on 2018/2/10.
 */
public class Page {

    /**
     * 当前页码
     */
    private int pageNo = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 5;
    /**
     * 当前查询条件下的记录数
     */
    private int totalCount = 0;

    /**
     * 当前查询条件下的总页数
     */
    private int totalPage = 1;
    /**
     * 开始行号
     */

    private int startNum = 0;

    /**
     * 结束行号
     */
    private int endNum = 0;

    /**
     * 当前查询条件的结果集
     */
    private List<?> list;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return
     */
    public int getTotalPage() {
        totalPage = totalCount/pageSize;
        if(totalCount == 0 || totalCount%pageSize != 0){
            totalPage++;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartNum() {
        return (pageNo - 1)*pageSize;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return pageNo*pageSize + 1;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
