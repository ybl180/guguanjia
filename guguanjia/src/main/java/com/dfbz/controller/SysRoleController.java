package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/8 10:58
 * @desciption
 */
@RestController
@RequestMapping("manager/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/role/role");
    }

    @RequestMapping("toList")
    public PageInfo<SysRole> toList(@RequestBody Map<String, Object> params) {
        return sysRoleService.selectPage(params);
    }

    @RequestMapping("toRoleUserPage")
    public ModelAndView toRoleUserPage() {
        return new ModelAndView("/role/role-user");
    }

    @RequestMapping("deleteBatch")
    public Result deleteBatch(Long rid, Long[] uids) {
        Result result = new Result();
        if (sysRoleService.deleteBatch(rid, uids) > 0) {
            result.setMsg("移除成功");
            result.setSuccess(true);
        }
        return result;
    }


    @RequestMapping("insertBatch")
    public Result insertBatch(Long rid, Long[] uids) {
//        List<Long> list = new ArrayList<>();
        //Arrays.asList(uids):   将传入的数组转换成list集合  只支持非基本类型数组转换集合list
        //所以接收方法参数需要设置成包装类的数组Long[]
        int i = sysRoleService.insertBatch(rid, Arrays.asList(uids));
        Result result = new Result();
        if (i > 0) {
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/role/role-save");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Map<String, Object> params) {
        Result result = new Result();
        if (sysRoleService.update(params) > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("deleteRole")
    public Result deleteRole(Long rid) {
        Result result = new Result();
        if (sysRoleService.deleteRole(rid) > 0) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage() {
        return new ModelAndView("/role/role-detail");
    }

    @RequestMapping("roleSelectOffice")
    public ModelAndView roleSelectOffice() {
        return new ModelAndView("/role/role-select");
    }

    @RequestMapping("saveRole")
    public Result saveRole(@RequestBody Map<String, Object> params) {
        Result result = new Result();
        if (sysRoleService.saveRole(params) > 0) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        }
        return result;
    }

}
