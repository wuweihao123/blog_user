package com.wwh.blog.vo;

import com.wwh.blog.pojo.User;
import com.wwh.springcloud.uitl.Convert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Convert<UserVo, User> {

    private String username;

    private String password;

    @Override
    public User vo2po(UserVo userVo) {
        User user = new User();
        if (!Objects.isNull(userVo)) {
            BeanUtils.copyProperties(userVo, user);
        }
        return user;
    }

    @Override
    public List<User> vos2pos(List<UserVo> list) {
        List<User> res = new ArrayList<>();
        if (!Objects.isNull(list)) {
            BeanUtils.copyProperties(list, res);
        }
        return res;
    }
}
