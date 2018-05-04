package com.libarymanagement.core.dao.member;

import com.libarymanagement.core.extEntity.PageWhere;
import com.libarymanagement.core.mapper.member.TbMemberMapper;
import com.libarymanagement.core.pojo.TbMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lee on 2018/4/24.
 */
@Repository
public class MemberDao {

    @Autowired
    private TbMemberMapper mapper;

    public int insert(TbMember record){
        return mapper.insert(record);
    }

    public int insertSelective(TbMember record){
        return mapper.insertSelective(record);
    }

    public TbMember selectByPrimaryKey(Long id){
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(TbMember record){
        try {
            return mapper.updateByPrimaryKeySelective(record);
        }catch (Exception e){
            //TODO  记录报错日志
            return 0;
        }
    }

    public int updateByPrimaryKey(TbMember record){
        return mapper.updateByPrimaryKey(record);
    }

    public List<TbMember> getListByCondition(TbMember member, PageWhere pageWhere) {
        return mapper.getListByCondition(member, pageWhere);
    }

    public int getCountByCondition(TbMember member){
        return mapper.getCountByCondition(member,null);
    }

}
