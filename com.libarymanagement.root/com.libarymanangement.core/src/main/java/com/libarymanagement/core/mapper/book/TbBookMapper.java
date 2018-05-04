package com.libarymanagement.core.mapper.book;


import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectBookListByCondition(@Param("book")Book book,@Param("pageWhere")PageWhere pageWhere);

    int selectCountByCondition(@Param("book")Book book);
}