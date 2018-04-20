package com.libarymanagement.core.mapper.book;


import com.libarymanagement.core.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}