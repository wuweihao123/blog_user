package com.wwh.blog.service;

import com.wwh.blog.pojo.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    User getUser(String username);

}
