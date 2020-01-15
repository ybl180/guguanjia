package com.dfbz.config;

import com.dfbz.aspect.LogAspect;
import com.dfbz.interceptor.LoginInterceptor;
import com.dfbz.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
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
@EnableAspectJAutoProxy     //开启切面注解支持
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * 由于spring的生命周期中，@Bean创建组件bean会先执行
     * 依赖注入操作会后执行，可以从容器中获取ResourceInterceptor对象
     */
    @Autowired
    ResourceInterceptor resourceInterceptor;

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

    @Bean
    public ResourceInterceptor getResourceInterceptor() {
        return new ResourceInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        //注册拦截器对象
        InterceptorRegistration loginRegistration = registry.addInterceptor(loginInterceptor);
        //设置拦截逻辑
        loginRegistration.addPathPatterns(new String[]{"/**"});//拦截所有请求
        //设置放行逻辑
        loginRegistration.excludePathPatterns(new String[]{"/toLogin", "/doLogin", "/index", "/manager/menu/selectByUid", "/manager/office/*"});
//        loginRegistration.order(1);

        InterceptorRegistration resourceRegistration = registry.addInterceptor(resourceInterceptor);
        resourceRegistration.addPathPatterns(new String[]{"/**"});
        resourceRegistration.excludePathPatterns(new String[]{"/toLogin", "/doLogin", "/index", "/manager/menu/selectByUid", "/manager/office/*"});
//        resourceRegistration.order(2);
    }

    @Bean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }

}
