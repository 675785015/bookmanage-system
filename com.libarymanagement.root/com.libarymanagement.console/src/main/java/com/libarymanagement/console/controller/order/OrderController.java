package com.libarymanagement.console.controller.order;

import com.libarymanagement.console.vm.OrderForm;
import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import com.libarymanagement.core.responseModel.base.*;
import com.libarymanagement.core.service.OrderService;
import com.libarymanagement.core.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/4/26.
 */
@Controller
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //借书单列表
    @RequestMapping("orderList")
    @ResponseBody
    public JsonResult orderList(OrderSearch order, PageWhere pageWhere){

        String s = ValidationUtil.validateModel(order);
        if(StringUtils.isNotBlank(s)){
            return new JsonResultError(s);
        }
        List<TbOrder> tbOrders = orderService.orderListByCondition(order, pageWhere);
        int i = orderService.countByCondition(order);

        return new JsonResultList<>(tbOrders, i);
    }
    //添加借书单
    @RequestMapping("addOrder")
    @ResponseBody
    public JsonResult addOrder(TbOrder order){
        String s = ValidationUtil.validateModel(order);
        if(StringUtils.isNotBlank(s)){
            return new JsonResultError(s);
        }
        int i = orderService.addOrUpdateOrder(order);
        if(i>0){
            return new JsonResultOk();
        }else{
            return new JsonResultError();
        }
    }
    //借书单详情
    @RequestMapping("orderDetail")
    @ResponseBody
    public JsonResult orderDetail(Long id){
        TbOrder order = orderService.getOrderById(id);
        if(order!=null){
            return new JsonResultData<>(order);
        }else{
            return new JsonResultError();
        }
    }
    //还书
    @RequestMapping("backBook")
    @ResponseBody
    public JsonResult backBook(long id){
        TbOrder order = new TbOrder();
        order.setId(id);
        order.setOrdReturnTime(new Date());
        order.setOrdStatus(CommonEntity.STATUS_ON);
        int i = orderService.addOrUpdateOrder(order);
        if(i>0){
            return new JsonResultOk();
        }else{
            return new JsonResultError();
        }
    }
    //更新书单
    @RequestMapping("updateOrder")
    @ResponseBody
    public JsonResult updateOrder(OrderForm order){
        String s = ValidationUtil.validateModel(order);
        if(StringUtils.isNotBlank(s)){
            return new JsonResultError(s);
        }
        TbOrder order1 = order.toEntity(order);
        int i = orderService.addOrUpdateOrder(order1);
        if(i>0){
            return new JsonResultOk();
        }else{
            return new JsonResultError();
        }

    }
}
