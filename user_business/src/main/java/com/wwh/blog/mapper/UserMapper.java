package com.wwh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwh.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
