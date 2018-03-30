package com.mybatis.usera.action;

import com.mybatis.usera.model.UserA;
import com.mybatis.usera.services.IUserService;
import com.mybatis.usera.services.impl.UserServiceImpl;
import com.mybatis.userb.model.UserB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/add",produces = {"application/json;charset=UTF-8"})
    private int addUser(UserB user){
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
}
