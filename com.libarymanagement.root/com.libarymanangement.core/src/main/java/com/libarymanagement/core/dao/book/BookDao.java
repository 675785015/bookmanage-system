package com.libarymanagement.core.dao.book;

import com.libarymanagement.core.mapper.book.BookMapper;
import com.libarymanagement.core.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee on 2018/4/20.
 */
@Repository
public class BookDao {

    @Autowired
    private BookMapper mapper;

    public int deleteByPrimaryKey(Long id){
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(Book record){
        return mapper.insert(record);
    }

    public int insertSelective(Book record){
        return mapper.insertSelective(record);
    }

    public Book selectByPrimaryKey(Long id){
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Book record){
        return updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Book record){
        return updateByPrimaryKey(record);
    }
}
