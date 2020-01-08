package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/role/role-save");
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
    public Result insertBatch(long rid, Long[] uids) {
        List<Long> list = new ArrayList<Long>();
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
}
