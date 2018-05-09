package com.libarymanagement.console.controller.account;

import com.libarymanagement.core.pojo.TbUser;
import com.libarymanagement.core.responseModel.base.JsonResult;
import com.libarymanagement.core.responseModel.base.JsonResultError;
import com.libarymanagement.core.responseModel.base.JsonResultOk;
import com.libarymanagement.core.service.UserService;
import com.libarymanagement.core.utils.ECPSMD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/5/9.
 */
@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String name, String password, String verifyCode, HttpSession session){
        //获得正确的验证码
        String picCode = (String) session.getAttribute("verifyCode");
        //判断验证码是否正确
        if(!StringUtils.equalsIgnoreCase(verifyCode, picCode)){
            return new JsonResultError("验证码不正确");
        }
        //根据用户名和密码来查询用户
        TbUser user = userService.selectUserByUserName(name);
        if(user != null){
            // 生成一个MD5加密计算摘要
            String s = ECPSMD5.GetMD5Code(password);
            if(s.equals(user.getPassword())){
                //把user对象放入session之中
                session.setAttribute("user", user);
                return new JsonResultOk("登录成功");
           }

        }

        return new JsonResultError("登录失败");

    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        //return "redirect:/home/index";
        return "/account/login";
    }
}
