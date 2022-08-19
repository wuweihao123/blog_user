package com.wwh.blog.service;

import com.wwh.blog.pojo.User;
import com.wwh.springcloud.exception.BusinessException;

public interface UserService {
    void addUser(User user);

    User getUser(String username);

    String login(User user) throws BusinessException;
}
