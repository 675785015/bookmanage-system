package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.order.OrderDao;
import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import com.libarymanagement.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Lee on 2018/4/26.
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public List<TbOrder> orderListByCondition(OrderSearch order, PageWhere pageWhere) {

        return orderDao.getListByCondition(order, pageWhere);
    }

    @Override
    public int countByCondition(OrderSearch order) {

        return orderDao.getCountByCondition(order);
    }

    @Override
    public int addOrUpdateOrder(TbOrder order) {

        if (order.getId() == null) {
            order.setOrdStatus(CommonEntity.STATUS_OFF);
            //获得当前时间的最小精度
            String number = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                number = number + random.nextInt(10);
            }

            order.setOrdNumber(number);
            order.setOrdBorrowTime(new Date());
            return orderDao.insert(order);
        } else {
            return orderDao.insertSelective(order);
        }
    }

    @Override
    public TbOrder getOrderById(long id) {
        TbOrder tbOrder = orderDao.selectByPrimaryKey(id);

        return tbOrder;
    }
}
