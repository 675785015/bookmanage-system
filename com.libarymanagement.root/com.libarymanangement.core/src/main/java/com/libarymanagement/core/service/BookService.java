package com.libarymanagement.core.service;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.Book;

import java.util.List;

/**
 * Created by Lee on 2018/4/20.
 */
public interface BookService {
    //insertOrUpdate record
    int insertOrUpdate(Book book);

    /**
     * select bookList by condition
     * @param book
     * @param pageWhere
     * @return
     */
    List<Book> getListByCondition(Book book, PageWhere pageWhere);

    /**
     * select count by condition
     * @param book
     * @return
     */
    int selectCountByCondition(Book book);

    /**
     * update book selective
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * select one by primary id
     * @param bookId
     * @return
     */
    Book getOneById(Long bookId);
}
