package com.libarymanagement.core.mapper.order;


import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbOrder;
import com.libarymanagement.core.requestModel.OrderSearch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface TbOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);

    List<TbOrder> getListByCondition(@PathVariable("order")OrderSearch record, @PathVariable("pageWhere")PageWhere pageWhere);

    int getCountByCondition(@PathVariable("order")OrderSearch record);
}