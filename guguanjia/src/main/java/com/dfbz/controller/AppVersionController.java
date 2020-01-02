package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.entity.Result;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 17:41
 * @desciption
 */
@RestController
@RequestMapping("manager/app")
public class AppVersionController {
    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/app/index");
    }

    @RequestMapping("toList")
    public PageInfo<AppVersion> toList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {
        return appVersionService.selectPage(pageNum, pageSize);
    }

    @RequestMapping("toUpdate")
    public AppVersion toUpdate(Integer id) {
        return appVersionService.selectByPrimaryKey(id);
    }

    @RequestMapping("tpUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/app/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody AppVersion appVersion) {
        Result result = new Result();
        appVersion.setUpdateDate(new Date());
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("insertApp")
    public Result insertApp(@RequestBody AppVersion appVersion) {
        Result result = new Result();
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
        appVersion.setDelFlag("0");
        int i = appVersionService.insertSelective(appVersion);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        }
        return result;
    }

    @RequestMapping("deleteApp")
    public Result deleteApp(Integer id) {
        Result result = new Result();
        int i = appVersionService.deleteByPrimaryKey(id);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }


}
