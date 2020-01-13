package com.dfbz.interceptor;

import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/13 19:33
 * @desciption
 */

/**
 * 权限认证拦截功能:
 * 1.获取系统所有权限 ，判断  用户请求权限是否在系统所有权限的控制范围，如果不在则放行
 * 2.如果在则需要进一步判断是否用户拥有的权限：
 * a.获取用户所有的权限，判断请求是否用户拥有的权限，如果是则放行
 * b.如果不是则返回到index页
 */
public class ResourceInterceptor implements HandlerInterceptor {
    @Autowired
    SysResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取系统所有权限  将查询数据放入缓存
        List<SysResource> sysResources = resourceService.selectAll();
        String uri = request.getRequestURI();
        boolean flag = false;//是否需要进一步授权判断标记
        for (SysResource sysResource : sysResources) {
            if (!StringUtils.isEmpty(sysResource.getUrl()) && uri.contains(sysResource.getUrl())) {
                flag = true;//找到匹配资源，需要进一步授权判断
                break;
            }
        }
        if (flag) {
            /*
            a.获取用户所有的权限，判断请求是否用户拥有的权限，如果是则放行
            b.如果不是则返回到index页
            */
            List<SysResource> resources = (List<SysResource>) request.getSession().getAttribute("resources");
            for (SysResource resource : resources) {
                if (!StringUtils.isEmpty(resource.getUrl()) && uri.contains(resource.getUrl())) {
                    return true;
                }
            }
        } else {
            return true;//在sysResources中没有匹配到，不需要授权，放行
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
