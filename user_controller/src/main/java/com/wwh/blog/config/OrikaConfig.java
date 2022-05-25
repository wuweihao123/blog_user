package com.wwh.blog.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfig {
    @Bean
    @ConditionalOnMissingBean(MapperFacade.class)
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    @ConditionalOnMissingBean(MapperFacade.class)
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }
}
