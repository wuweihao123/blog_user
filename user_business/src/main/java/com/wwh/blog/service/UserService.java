package com.wwh.blog.service;

import com.wwh.blog.pojo.User;

public interface UserService {
    void addUser(User user);

    User getUser(String username);

}
