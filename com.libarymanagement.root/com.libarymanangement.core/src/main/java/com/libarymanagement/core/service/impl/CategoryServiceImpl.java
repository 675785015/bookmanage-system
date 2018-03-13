package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.category.CategoryDao;
import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.pojo.Category;
import com.libarymanagement.core.responseModel.base.JsonResultError;
import com.libarymanagement.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/2/22.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryDao dao;

    @Override
    public List<Category> getCategorys(Long parentId) {

        List<Category> categories = dao.getListByParentId(parentId);

        return categories;
    }

    @Override
    public synchronized int addUpdateDelCategory(Category category) {

        int count = 0;
        //判断是否存在主键id 添加
        if(category.getId()==null){
            if(category.getParentId() != null){
                Category parentCategory = selectByPrimaryKey(category.getParentId());
                if(parentCategory==null){
                    return count;
                }
            }
            category.setCreateTime(new Date());
            category.setUpdateTime(category.getCreateTime());
            category.setIsDel(CommonEntity.ISDEL_FALSE);
            //新增
            count = dao.insert(category);
            return count;
        }
        //删除
        if(category.getIsDel()==1){
            //是否存在下级分类
            List<Category> categorys = getCategorys(category.getId());

            if (categorys != null && categorys.size() > 0) {
                return count;
            }
        }
        //更新
        category.setUpdateTime(new Date());
        count = dao.updateByPrimaryKeySelective(category);

        return count;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        int i = dao.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Category selectByPrimaryKey(Long id) {
        Category category = dao.selectByPrimaryKey(id);
        return category;
    }

}
