package com.dfbz.aspect;

import com.dfbz.entity.SysLog;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysLogService;
import com.dfbz.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/14 15:36
 * @desciption
 */
@Aspect
public class LogAspect {
    @Autowired
    SysLogService logService;

    //spring容器会自动的将原生request封装成一个子类，通过注入方式注入spring管理的子类
    @Autowired
    HttpServletRequest request;

    @Pointcut(value = "within(com.dfbz.controller.*Controller)")
    public void pointcut() {
    }

    /* 正常日志
     * @param joinPoint 连接点（方法）对象
     */
    @AfterReturning(pointcut = "pointcut()", returning = "obj")
    public void afterReturning(JoinPoint joinPoint, Object obj) {
        saveLog(joinPoint, null);
    }

    //异常日志
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        saveLog(joinPoint, e);
    }

    /* 保存SysLog  对象
     * 需要获取
     * 1.request
     * 2.session
     * 3.joinpoint
     * 4.exception
     * 5.logService
     */
    private void saveLog(JoinPoint joinPoint, Exception e) {
        SysLog sysLog = new SysLog();
        sysLog.setType(e == null ? "1" : "2");//判断e是否空  如果空则是正常日志
        sysLog.setException(e == null ? "" : e.toString());
        if (request != null) {
            sysLog.setRemoteAddr(IPUtils.getClientAddress(request));
            sysLog.setRequestUri(request.getRequestURI());
            sysLog.setUserAgent(request.getHeader("User-Agent"));
            SysUser userInfo = (SysUser) request.getSession().getAttribute("userInfo");
            sysLog.setMethod(request.getMethod());
            if (userInfo != null) {
                sysLog.setCreateBy(userInfo.getName());
            }
            sysLog.setCreateDate(new Date());
        }
        Object[] args = joinPoint.getArgs();
        //TODO
        if (args != null && args.length > 0 ) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if(args[i]!=null){
                    String typeName = args[i].getClass().getSimpleName();
                    sb.append("[参数" + 1 + i + ",类型:" + typeName + ",值:" + args[i].toString() + "],");
                }
            }
            if(sb.length()>0){
                sb.deleteCharAt(sb.length() - 1);
            }
            sysLog.setParams(sb.toString());
        }

        logService.insert(sysLog);
    }
}
