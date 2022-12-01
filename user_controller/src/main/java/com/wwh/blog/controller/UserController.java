package com.wwh.blog.controller;

import com.alibaba.fastjson2.JSON;
import com.wwh.blog.face.AccessLimit;
import com.wwh.blog.pojo.User;
import com.wwh.blog.service.UserService;
import com.wwh.blog.vo.UserVo;
import com.wwh.springcloud.exception.BusinessException;
import com.wwh.springcloud.pojo.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户模块")
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final MapperFacade mapperFacade;

    public UserController(UserService userService,
                          MapperFacade mapperFacade) {
        this.userService = userService;
        this.mapperFacade = mapperFacade;

    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public ResultMessage register(@Validated  @RequestBody UserVo userVo) {
        log.info("UserController login = {}", JSON.toJSON(userVo));
        User user = mapperFacade.map(userVo, User.class);
        userService.addUser(user);
        return new ResultMessage();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public ResultMessage login(@Validated @RequestBody UserVo userVo) throws BusinessException {
        log.info("UserController login = {}", JSON.toJSON(userVo));
        User user = mapperFacade.map(userVo, User.class);
        String token = userService.login(user);
        return new ResultMessage(token);
    }

    @ApiOperation(value = "获取去具体某个用户", notes = "获取具体某个用户")
    @GetMapping("/getUser")
    @AccessLimit(maxCount = 10, second = 60)
    public ResultMessage getUser(@RequestParam(name = "username") String username) {
        log.info("UserController getUser = {} ", username);
        User user = userService.getUser(username);
        UserVo userVo = user.po2vo();
        return new ResultMessage(userVo);
    }


}
