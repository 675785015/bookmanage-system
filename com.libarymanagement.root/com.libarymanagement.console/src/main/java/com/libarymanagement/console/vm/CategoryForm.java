package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by Lee on 2018/2/8.
 */
public class CategoryForm {
    private Long parentId;

    private Long id;
    @NotBlank(message = "分类名称不能为空")
    @Size(min=1,max=20,message = "名称不能多于20个字符")
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
