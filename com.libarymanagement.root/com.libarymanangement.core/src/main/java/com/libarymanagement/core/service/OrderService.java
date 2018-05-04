package com.libarymanagement.core.service;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import java.util.List;

/**
 * Created by Lee on 2018/4/26.
 */
public interface OrderService {

    List<TbOrder> orderListByCondition(OrderSearch order, PageWhere pageWhere);

    int countByCondition(OrderSearch order);

    int addOrUpdateOrder(TbOrder order);

    TbOrder getOrderById(long id);
}
