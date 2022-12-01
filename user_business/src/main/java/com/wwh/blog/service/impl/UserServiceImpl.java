package com.wwh.blog.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wwh.blog.mapper.UserMapper;
import com.wwh.blog.pojo.User;
import com.wwh.blog.service.UserService;
import com.wwh.springcloud.exception.BusinessException;
import com.wwh.springcloud.uitl.BaseServiceImpl;
import com.wwh.springcloud.uitl.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wuweihao5
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(User user) {
        user.setSalt("12331232131");
        userMapper.insert(user);
        log.info("添加成功");
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        //return userMapper.selectOne(queryWrapper);
        return userMapper.selectByName(username);
    }

    @Override
    public String login(User user) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getPassword());
        queryWrapper.eq("password", user.getPassword());
        User res = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(res)) {
            throw new BusinessException("用户名密码错误");
        }
        JSONObject json = new JSONObject();
        json.put("username", res.getUsername());
        json.put("password", res.getPassword());
        json.put("userId", res.getId());
        return TokenUtils.createToken(json.toJSONString());
    }
}
