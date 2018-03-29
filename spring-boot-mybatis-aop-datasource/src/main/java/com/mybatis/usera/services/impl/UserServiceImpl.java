package com.mybatis.usera.services.impl;

import com.github.pagehelper.PageHelper;
import com.mybatis.annotation.SetDataSource;
import com.mybatis.druiddatasource.DataSourceType;
import com.mybatis.usera.mapper.UserAMapper;
import com.mybatis.usera.model.UserA;
import com.mybatis.usera.services.IUserService;
import com.mybatis.userb.mapper.UserBMapper;
import com.mybatis.userb.model.UserB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBMapper userBMapper;

    @Autowired
    private UserAMapper userAMapper;

    @SetDataSource(value = DataSourceType.Slave)
    public int addUser(UserB user) {
        return userBMapper.insert(user);
    }

    @SetDataSource(value = DataSourceType.Master)
    public Object findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userAMapper.selectAll();
    }
}