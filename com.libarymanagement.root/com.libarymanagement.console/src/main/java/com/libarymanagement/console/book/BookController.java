package com.libarymanagement.console.book;

import com.libarymanagement.console.vm.BookForm;
import com.libarymanagement.core.pojo.Book;
import com.libarymanagement.core.responseModel.base.JsonResult;
import com.libarymanagement.core.responseModel.base.JsonResultError;
import com.libarymanagement.core.responseModel.base.JsonResultOk;
import com.libarymanagement.core.service.BookService;
import com.libarymanagement.core.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lee on 2018/3/13.
 */
@Controller
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    //列表
    public JsonResult list(){
        return new JsonResultError();
    }

    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addBook(BookForm form){

        String valideInfo = ValidationUtil.validateModel(form);
        if(StringUtils.isNotBlank(valideInfo)){
            return new JsonResultError(valideInfo);
        }

        Book book = form.toEntity(form);

        int insert = bookService.insert(book);
        if(insert>0){
            return new JsonResultOk("添加成功");
        }else{
            return new JsonResultError("添加失败");
        }
    }
}
