package com.libarymanagement.console;

import org.springframework.stereotype.Controller;
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
}
