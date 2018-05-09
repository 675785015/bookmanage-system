package com.libarymanagement.core.service.impl;

import com.libarymanagement.core.dao.user.UserDao;
import com.libarymanagement.core.pojo.TbUser;
import com.libarymanagement.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2018/5/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;
    @Override
    public TbUser selectUserByUserName(String username) {
        return dao.selectUserByUserName(username);
    }
}
