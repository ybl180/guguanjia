package com.dfbz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 17:34
 * @desciption
 */

/**
 * springmvc配置
 * 1.实现WebMvcConfigurer
 * 2.开启mvc注解支持
 * 3.开启扫描controller层
 * 4.静态资源放行等mvc配置
 */
@Configuration
@ComponentScan(basePackages = "com.dfbz.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getResourceViewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/html", ".html");
    }

    //配置文件上传解析器
    @Bean("multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        return new CommonsMultipartResolver();
    }
}
