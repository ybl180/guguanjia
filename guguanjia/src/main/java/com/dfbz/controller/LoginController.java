package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 14:52
 * @desciption
 */
@Controller
public class LoginController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public Result doLogin(@RequestBody Map<String, Object> params, HttpSession session) {
        Result result = new Result();
        //从session获取code的值
        String vcode = (String) session.getAttribute("vcode");
        if (vcode.equals(params.get("code"))) { //验证码正确
            SysUser sysUser = new SysUser();
            sysUser.setUsername((String) params.get("username"));
            sysUser.setPassword((String) params.get("password"));
            SysUser checkSysUser = sysUserService.checkSysUser(sysUser);
            if (checkSysUser != null) {//登录成功
                result.setSuccess(true);
                result.setMsg("登录成功");
                result.setObj(checkSysUser);
                //将用户信息放入session
//                session.setAttribute("userInfo", checkSysUser);
            }
        }
        return result;
    }

    @RequestMapping("index")
    public String index() {
        return "/index";
    }

}
