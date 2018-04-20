package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.book.BookDao;
import com.libarymanagement.core.pojo.Book;
import com.libarymanagement.core.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2018/4/20.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public int insert(Book book) {

        return bookDao.insert(book);
    }
}
