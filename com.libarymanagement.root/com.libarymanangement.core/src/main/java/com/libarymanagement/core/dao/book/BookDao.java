package com.libarymanagement.core.dao.book;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.mapper.book.TbBookMapper;
import com.libarymanagement.core.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lee on 2018/4/20.
 */
@Repository
public class BookDao {

    @Autowired
    private TbBookMapper mapper;

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
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Book record){
        return mapper.updateByPrimaryKey(record);
    }

    public List<Book> selectBookListByCondition(Book book, PageWhere pageWhere){
        return mapper.selectBookListByCondition(book,pageWhere);
    }

    public int selectCountByCondition(Book book){
        return mapper.selectCountByCondition(book);
    }

}
