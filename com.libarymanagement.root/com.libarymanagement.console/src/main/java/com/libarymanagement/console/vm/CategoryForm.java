package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.Category;

import java.util.Locale;

/**
 * Created by Lee on 2018/2/8.
 */
public class CategoryForm {
    private Long parentId;

    private Long id;

    private String name;


    public Long getParentId() {
        return parentId;
    }

    public Category getEntity(CategoryForm form){
        Category category = new Category();
        category.setName(form.getName());
        category.setId(form.getId());
        category.setParentId(form.getParentId());

        return category;
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
}
