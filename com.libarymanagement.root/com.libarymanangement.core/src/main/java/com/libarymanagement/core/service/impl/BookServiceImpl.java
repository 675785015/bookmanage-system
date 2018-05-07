package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.book.BookDao;
import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.Book;
import com.libarymanagement.core.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/4/20.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public int insertOrUpdate(Book book) {

        if(book.getId()==null){
            // 查询isbn是否存在
            Book exist = new Book();
            exist.setIsbn(book.getIsbn());
            int i = bookDao.selectCountByCondition(exist);
            if(i>0){
                return 0;
            }
            book.setCreateTime(new Date());
            book.setUpdateTime(book.getCreateTime());
            book.setOnShelf(CommonEntity.STATUS_OFF);
            return bookDao.insert(book);
        }else{
            book.setUpdateTime(new Date());
            return bookDao.updateByPrimaryKeySelective(book);
        }
    }

    @Override
    public int selectCountByCondition(Book book) {
        return bookDao.selectCountByCondition(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateByPrimaryKeySelective(book);
    }

    @Override
    public Book getOneById(Long bookId) {
        return bookDao.selectByPrimaryKey(bookId);
    }

    @Override
    public List<Book> getListByCondition(Book book, PageWhere pageWhere){
        return bookDao.selectBookListByCondition(book,pageWhere);
    }
}
