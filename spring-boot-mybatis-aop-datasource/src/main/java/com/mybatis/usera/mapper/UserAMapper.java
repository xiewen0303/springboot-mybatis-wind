package com.mybatis.usera.mapper;

import com.mybatis.usera.model.UserA;

import java.util.List;

public interface UserAMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserA record);

    int insertSelective(UserA record);

    UserA selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserA record);

    int updateByPrimaryKey(UserA record);

    List<UserA> selectAll();
}