package com.wwh.blog.controller;

import com.alibaba.fastjson2.JSON;
import com.wwh.blog.pojo.User;
import com.wwh.blog.service.UserService;
import com.wwh.blog.vo.UserVo;
import com.wwh.springcloud.pojo.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultMessage login(@RequestBody UserVo userVo) {
        log.info("UserController login = {}", JSON.toJSON(userVo));

        User user = userVo.vo2po(userVo);
        userService.addUser(user);
        return new ResultMessage();
    }

}
