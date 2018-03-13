package com.libarymanagement.core.service;

import com.libarymanagement.core.pojo.Category;

import java.util.List;
import java.util.Locale;

/**
 * Created by Lee on 2018/2/22.
 */
public interface CategoryService {

    List<Category> getCategorys(Long parentId);

    //添加或修改
    int addUpdateDelCategory(Category category);

    int deleteByPrimaryKey(Long id);

    Category selectByPrimaryKey(Long id);
}
