package com.wwh.blog.interceptor;

import com.wwh.blog.face.AccessLimit;
import com.wwh.springcloud.exception.AccessLimitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            //判断Handler是否为HandlerMethod实例
            if (handler instanceof HandlerMethod) {

                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                if (!method.isAnnotationPresent(AccessLimit.class)) {
                    return true;
                }

                //获取注解内容信息
                AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
                if (Objects.isNull(accessLimit)) {
                    return true;
                }

                int maxCount = accessLimit.maxCount();
                int second = accessLimit.second();

                String key = request.getRemoteHost() + ":" + request.getContextPath() + ":" + request.getServletPath();

                Integer count = (Integer) redisTemplate.opsForValue().get(key);
                if (null == count || -1 == count) {
                    redisTemplate.opsForValue().set(key, 1, second, TimeUnit.SECONDS);
                    return true;
                }

                if (count < maxCount) {
                    redisTemplate.opsForValue().increment(key);
                    return true;
                }

                if (count >= maxCount) {
                    throw new AccessLimitException("请求过于频繁，请稍后再试！");
                }
            }
            return true;
        } catch (Exception e) {
            throw new AccessLimitException("请求过于频繁，请稍后再试！");
        }
    }
}
