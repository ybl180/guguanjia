package com.dfbz.controller;

import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/26 17:09
 * @desciption
 */
@RestController
@RequestMapping("manager/examine")
public class ExamineController {
    @Autowired
    ExamineService examineService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/examine/index");
    }

    @RequestMapping("toList")
    public PageInfo<Examine> toList(@RequestBody Map<String, Object> params) {
        return examineService.selectPage(params);
    }

}
