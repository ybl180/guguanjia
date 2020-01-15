package com.dfbz.controller;

import com.dfbz.entity.SysLog;
import com.dfbz.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/14 15:07
 * @desciption
 */
@RestController
@RequestMapping("manager/syslog")
public class SysLogController {
    @Autowired
    SysLogService sysLogService;

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/log/log");
    }

    @RequestMapping("toList")
    public PageInfo<SysLog> selectPage(@RequestBody Map<String,Object> params){
        return sysLogService.selectByCondition(params);
    }
}
