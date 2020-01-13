package com.dfbz.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/13 19:20
 * @desciption
 */

/**
 * springmvc的拦截器：
 * 1.编写一个类实现HandlerInterceptor接口
 * 2.重写三个拦截时机方法逻辑
 * 3.将拦截器注册为spring的拦截器对象，设置拦截和放行逻辑
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     *登录拦截：
     * 1.获取状态管理中的用户对象，如果存在则放行
     * 2.如果不存在则跳转到登录页
     *
     * @param request
     * @param response
     * @param handler       目标方法
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理请求的controller执行前
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if (userInfo != null) {
            return true;
        }
        request.getRequestDispatcher("/toLogin").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //controller方法执行完
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //处理完视图，返回到页面前
    }
}
