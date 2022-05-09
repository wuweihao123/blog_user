package com.wwh.blog.service.impl;

import com.wwh.blog.pojo.User;
import com.wwh.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void addUser(User user) {
        log.info("添加成功");
    }
}
