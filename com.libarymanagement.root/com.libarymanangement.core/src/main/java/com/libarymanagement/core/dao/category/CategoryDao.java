package com.libarymanagement.core.dao.category;

import com.libarymanagement.core.mapper.category.CategoryMapper;
import com.libarymanagement.core.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lee on 2018/2/28.
 */
@Repository
public class CategoryDao {

    @Autowired
    public CategoryMapper mapper;

    public int deleteByPrimaryKey(Long id){
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    };

    public int insert(Category record){
        int insert = mapper.insert(record);
        return insert;
    };

    public int insertSelective(Category record){
        int i = mapper.insertSelective(record);
        return i;
    };

    public Category selectByPrimaryKey(Long id){
        Category category = mapper.selectByPrimaryKey(id);
        return category;
    };

    public int updateByPrimaryKeySelective(Category record){
        int i = mapper.updateByPrimaryKeySelective(record);
        return i;
    };

    public int updateByPrimaryKey(Category record){
        int i = mapper.updateByPrimaryKey(record);
        return i;
    };


    public List<Category> getListByParentId(Long parentId){
        Category category = new Category();
        category.setIsDel(0);
        category.setParentId(parentId);
        List<Category> categories = mapper.getListByParentId(category);
        return categories;
    }
}
