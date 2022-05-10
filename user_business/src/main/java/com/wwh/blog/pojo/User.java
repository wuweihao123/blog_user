package com.wwh.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wwh.blog.vo.UserVo;
import com.wwh.springcloud.uitl.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@TableName(value = "blog_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Convert<UserVo, User> {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "salt")
    private String salt;

    @Override
    public void vo2po(UserVo userVo) {
        if (!Objects.isNull(userVo)) {
            BeanUtils.copyProperties(userVo, this);
        }

    }

    @Override
    public List<User> vos2pos(List<UserVo> list) {
        List<User> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            User user = new User();
            for (UserVo userVo : list) {
                BeanUtils.copyProperties(userVo, user);
                res.add(user);
            }
        }
        return res;
    }

    @Override
    public UserVo po2vo() {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(this, userVo);
        return userVo;
    }

    @Override
    public List<UserVo> pos2vos(List<User> list) {
        List<UserVo> userVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (User user : list) {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user, userVo);
                userVos.add(userVo);
            }
        }
        return userVos;
    }
}
