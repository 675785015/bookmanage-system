package com.libarymanagement.core.mapper.member;


import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface TbMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbMember record);

    int insertSelective(TbMember record);

    TbMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbMember record);

    int updateByPrimaryKey(TbMember record);

    List<TbMember> getListByCondition(@Param("member")TbMember member, @Param("pageWhere")PageWhere pageWhere);

    int getCountByCondition(@Param("member")TbMember member, @Param("pageWhere")PageWhere pageWhere);
}