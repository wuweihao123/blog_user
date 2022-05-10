package com.wwh.blog.controller;

import com.alibaba.fastjson2.JSON;
import com.wwh.blog.pojo.User;
import com.wwh.blog.service.UserService;
import com.wwh.blog.vo.UserVo;
import com.wwh.springcloud.pojo.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResultMessage login(@Validated  @RequestBody UserVo userVo) {
        log.info("UserController login = {}", JSON.toJSON(userVo));
        User user = new User();
        user.vo2po(userVo);
        userService.addUser(user);
        return new ResultMessage();
    }

    @GetMapping("/getUser")
    public ResultMessage getUser(@RequestParam(name = "username") String username) {
        log.info("UserController getUser = {} ", username);
        User user = userService.getUser(username);
        UserVo userVo = user.po2vo();
        return new ResultMessage(userVo);
    }


}
