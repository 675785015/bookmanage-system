package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.member.MemberDao;
import com.libarymanagement.core.extEntity.CommonEntity;
import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.pojo.TbMember;
import com.libarymanagement.core.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/4/24.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<TbMember> getListByCondition(TbMember member, PageWhere pageWhere) {
        return memberDao.getListByCondition(member, pageWhere);
    }

    @Override
    public int getCountByCondition(TbMember member) {
        return memberDao.getCountByCondition(member);
    }

    @Override
    public int addOrUpdateMember(TbMember member) {

        if(member.getId()!=null){//update
            return memberDao.updateByPrimaryKeySelective(member);
        }else{
            if(StringUtils.isNotBlank(member.getCardNumber())){
                TbMember tbMember = new TbMember();
                tbMember.setCardNumber(member.getCardNumber());
                int count = memberDao.getCountByCondition(tbMember);
                if(count>0){    //编号已存在
                    return 0;
                }
            }
            member.setRegisterDatetime(new Date());
            member.setStatus(CommonEntity.STATUS_ON);
            return memberDao.insert(member);
        }
    }

    @Override
    public TbMember getMemberById(Long id) {
        return memberDao.selectByPrimaryKey(id);
    }
}
