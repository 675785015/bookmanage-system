package com.libarymanagement.core.pojo;

import java.util.List;

/**
 * Created by Lee on 2018/2/8.
 */
public class Category {

    private Long parentId;

    private Long id;

    private String name;

    private List<Category> categoryList;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
