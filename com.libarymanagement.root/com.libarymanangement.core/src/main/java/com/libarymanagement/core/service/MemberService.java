package com.libarymanagement.core.service;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbMember;

import javax.validation.Payload;
import java.util.List;

/**
 * Created by Lee on 2018/4/24.
 */
public interface MemberService {
    //条件查询会员列表
    List<TbMember> getListByCondition(TbMember member, PageWhere pageWhere);

    int getCountByCondition(TbMember member);

    int addOrUpdateMember(TbMember member);

    TbMember getMemberById(Long id);
}
