package com.mybatis.usera.services;

import com.mybatis.usera.model.UserA;
import com.mybatis.userb.model.UserB;

public interface IUserService {

    public int addUser(UserB user);

    public Object findAllUser(int pageNum, int pageSize);
}
