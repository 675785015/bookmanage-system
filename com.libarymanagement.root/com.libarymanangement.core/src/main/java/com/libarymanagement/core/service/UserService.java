package com.libarymanagement.core.service;

import com.libarymanagement.core.pojo.TbUser;

/**
 * Created by Lee on 2018/5/9.
 */
public interface UserService {

    TbUser selectUserByUserName(String username);
}
