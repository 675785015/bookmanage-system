package com.libarymanagement.console.controller.book;

import com.libarymanagement.console.vm.BookForm;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.Book;
import com.libarymanagement.core.responseModel.base.*;
import com.libarymanagement.core.service.BookService;
import com.libarymanagement.core.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Lee on 2018/3/13.
 */
@Controller
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    //列表
    @RequestMapping("booklist")
    @ResponseBody
    public JsonResult list(Integer onShelfState, PageWhere pageWhere){

        if(onShelfState != null){
            Book book = new Book();
            book.setOnShelf(onShelfState);

            List<Book> listByState = bookService.getListByCondition(book, pageWhere);
            int i = bookService.selectCountByCondition(book);
            return new JsonResultList<>(listByState, i);
        }

        return new JsonResultError("请求错误");
    }

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addBook(BookForm form){

        String valideInfo = ValidationUtil.validateModel(form);
        if(StringUtils.isNotBlank(valideInfo)){
            return new JsonResultError(valideInfo);
        }

        Book book = form.toEntity(form);

        int insert = bookService.insertOrUpdate(book);
        if(insert>0){
            return new JsonResultOk("添加成功");
        }else{
            return new JsonResultError("添加失败");
        }
    }

    @RequestMapping(value = "updateBook", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateBook(BookForm form){

        String valideInfo = ValidationUtil.validateModel(form);
        if(StringUtils.isNotBlank(valideInfo)){
            return new JsonResultError(valideInfo);
        }

        Book book = form.toEntity(form);

        int insert = bookService.insertOrUpdate(book);
        if(insert>0){
            return new JsonResultOk("修改成功");
        }else{
            return new JsonResultError("修改失败");
        }
    }


    @RequestMapping("onshelf")
    @ResponseBody
    public JsonResult onshelf(@NotNull Long bookId, @NotNull @Size(max = 1,min = 0)Integer status){
        Book book = new Book();
        book.setOnShelf(status);
        book.setId(bookId);
        int i = bookService.insertOrUpdate(book);
        if(i>0){
            return new JsonResultOk("修改成功");
        }
        return new JsonResultError("修改失败");
    }

    @RequestMapping("getbook")
    @ResponseBody
    public JsonResult getBook(Long bookId){
        Book book = bookService.getOneById(bookId);
        if(book!=null){
            return new JsonResultData<>(book);
        }else{
            return new JsonResultError("查询不存在");
        }
    }


}
