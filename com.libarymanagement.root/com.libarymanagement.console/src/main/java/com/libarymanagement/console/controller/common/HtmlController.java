package com.libarymanagement.console.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lee on 2018/2/24.
 */
@Controller
public class HtmlController {

    @RequestMapping("/")
    public String index(){
        return "home/index";
    }

    @RequestMapping("{doc}/{html}")
    public String pages(@PathVariable String doc, @PathVariable String html){
        return doc+"/"+html;
    }

    @RequestMapping("toAddBook/{categoryId}/{categoryName}")
    public String toAddBookPage(@PathVariable("categoryId")long id, @PathVariable("categoryName")String name, Model model){
        model.addAttribute("categoryId",id);
        model.addAttribute("categoryField",name);
        return "book/add";
    }

    @RequestMapping("toBookDetail/{bookId}")
    public String toBookDetail(@PathVariable("bookId")long bookId,Model model){
        model.addAttribute("bookId",bookId);
        return "book/detail";
    }

    @RequestMapping("toMemberDetail/{id}")
    public String toMemberDetail(@PathVariable("id")long id,Model model){
        model.addAttribute("id",id);
        return "member/detail";
    }

    @RequestMapping("toAddOrder/{id}")
    public String toAddOrder(@PathVariable("id")long id,Model model){
        model.addAttribute("memberId",id);
        return "order/add";
    }

    @RequestMapping("toOrderDetail/{id}")
    public String toOrderDetail(@PathVariable("id")long id, Model model){
        model.addAttribute("orderId", id);
        return "order/detail";
    }
}
