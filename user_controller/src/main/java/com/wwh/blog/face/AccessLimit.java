package com.wwh.blog.face;

import java.lang.annotation.*;

/**
 * @author wuweihao5
 */

@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /**
     * 指定second时间内 api请求的次数
     */
    int maxCount() default 5;

    /**
     * 指定次数的指定时间方位 描述(redis数据过期时间)
     */
    int second() default 60;

}
