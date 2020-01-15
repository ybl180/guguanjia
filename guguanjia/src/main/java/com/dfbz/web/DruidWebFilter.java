package com.dfbz.web;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/14 17:42
 * @desciption
 */

/*
 * 开启web功能监控的过滤器
 */
@WebFilter(urlPatterns = "/",initParams = {
        @WebInitParam(name = "exclusions",value="*.js,*.css,*.jpg,*.png,/druid/*"),
        @WebInitParam(name = "profileEnable",value = "true"),//设置单个url的调用sql列表
        @WebInitParam(name = "principalSessionName",value="userInfo")
})
public class DruidWebFilter extends WebStatFilter {
}
