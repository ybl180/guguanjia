package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysRole;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysRoleService;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 16:16
 * @desciption
 */
@RestController
@RequestMapping("manager/sysuser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/user/user-list");
    }

    @RequestMapping("toList")
    public PageInfo<SysUser> toList(@RequestBody Map<String, Object> params) {
        return sysUserService.selectPage(params);
    }

    @RequestMapping("selectRole")
    public List<SysRole> createRole() {
        return sysRoleService.selectAllRole();
    }

    @RequestMapping("selectByRid")
    public List<SysUser> selectBydRid(Long rid) {
        return sysUserService.selectByRid(rid);
    }

    @RequestMapping("selectNoRole")
    public List<SysUser> selectNoRole(long rid, long oid) {
        return sysUserService.selectNoRole(rid, oid);
    }

    @RequestMapping("detail")
    public ModelAndView detail() {
        return new ModelAndView("/user/user-detail");
    }
}
