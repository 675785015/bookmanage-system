package com.libarymanagement.core.dao.order;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.mapper.order.TbOrderMapper;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Lee on 2018/4/24.
 */
@Repository
public class OrderDao {

    @Autowired
    private TbOrderMapper mapper;

    public int insert(TbOrder record){
        return mapper.insert(record);
    }

    public int insertSelective(TbOrder record){
        return mapper.insertSelective(record);
    }

    public TbOrder selectByPrimaryKey(Long id){
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(TbOrder record){
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TbOrder record){
        return mapper.updateByPrimaryKey(record);
    }

    public List<TbOrder> getListByCondition(OrderSearch record, PageWhere pageWhere){
        return mapper.getListByCondition(record, pageWhere);
    }

    public int getCountByCondition(OrderSearch record){
        return mapper.getCountByCondition(record);
    }
}
