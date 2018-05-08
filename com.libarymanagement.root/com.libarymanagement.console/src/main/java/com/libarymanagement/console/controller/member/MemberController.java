package com.libarymanagement.console.controller.member;

import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbMember;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import com.libarymanagement.core.responseModel.base.*;
import com.libarymanagement.core.service.MemberService;
import com.libarymanagement.core.service.OrderService;
import com.libarymanagement.core.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/4/24.
 */
@Controller
@RequestMapping("api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;
    //注销会员
    @RequestMapping("logoutMember")
    @ResponseBody
    public JsonResult logoutMember(@NotNull(message = "主键不能为空")Long id, String reason){

        String s = ValidationUtil.validateModel(id);
        if(StringUtils.isNotBlank(s)){
            return new JsonResultError(s);
        }
        //查询是否有未还的图书
        OrderSearch searchobj = new OrderSearch();
        searchobj.setMemberId(id);
        searchobj.setStatus(CommonEntity.STATUS_ON);
        int orderCount = orderService.countByCondition(searchobj);
        if(orderCount>0){
            return new JsonResultError("注销失败，有未归还的图书");
        }

        TbMember member = new TbMember();
        member.setId(id);
        member.setLogoutReason(reason);
        member.setStatus(CommonEntity.STATUS_OFF);
        member.setLogoutDatetime(new Date());
        int i = memberService.addOrUpdateMember(member);
        if(i>0){
            return new JsonResultOk("注销成功");
        }else{
            return new JsonResultError("注销失败");
        }

    }

    //添加会员
    @RequestMapping("addMember")
    @ResponseBody
    public JsonResult addMember(TbMember member){
        //校验对象
        String s = ValidationUtil.validateModel(member);
        if(StringUtils.isNotBlank(s)){
            return new JsonResultError(s);
        }
        int i = memberService.addOrUpdateMember(member);
        if(i>0){
            return new JsonResultOk("添加成功");
        }else{
            return new JsonResultError("添加失败");
        }

    }
    //会员列表 分页
    @RequestMapping("getMemberList")
    @ResponseBody
    public JsonResult getMemeberList(TbMember member, PageWhere pageWhere){

        List<TbMember> listByCondition = memberService.getListByCondition(member, pageWhere);

        int countByCondition = memberService.getCountByCondition(member);

        return new JsonResultList<>(listByCondition, countByCondition);
    }

    //会员详情
    @RequestMapping("getMemberDetail")
    @ResponseBody
    public JsonResult getMemberDetail(Long id){
        TbMember member = memberService.getMemberById(id);
        return new JsonResultData<>(member);
    }

    //更新会员
    @RequestMapping("updateMember")
    @ResponseBody
    public JsonResult updateMember(TbMember member){
        if(member.getId()==null){
            return new JsonResultError("会员不存在");
        }
        int i = memberService.addOrUpdateMember(member);
        if(i>0){
            return new JsonResultOk("更新成功");
        }else{
            return new JsonResultError("更新失败");
        }
    }
}
