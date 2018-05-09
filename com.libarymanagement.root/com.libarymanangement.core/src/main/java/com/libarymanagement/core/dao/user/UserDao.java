package com.libarymanagement.core.dao.user;

import com.libarymanagement.core.mapper.user.TbUserMapper;
import com.libarymanagement.core.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee on 2018/5/9.
 */
@Repository
public class UserDao {

    @Autowired
    private TbUserMapper mapper;

    public TbUser selectUserByUserName(String username){
        return mapper.selectUserByUserName(username);
    }
}
