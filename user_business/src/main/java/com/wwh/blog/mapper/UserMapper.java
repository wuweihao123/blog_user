package com.wwh.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwh.blog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户实体
     */
    User selectByName(@Param("username") String username);
}
