package com.mybatis.userb.mapper;

import com.mybatis.userb.model.UserB;

public interface UserBMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserB record);

    int insertSelective(UserB record);

    UserB selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserB record);

    int updateByPrimaryKey(UserB record);
}