package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.entity.Result;
import com.dfbz.service.QualificationService;
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
 * @date 2019/12/25 16:36
 * @desciption
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController {
    @Autowired
    QualificationService qualificationService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/qualification/index");
    }

    @RequestMapping("toList")
    public PageInfo<Qualification> toList(@RequestBody Map<String, Object> params) {
        return qualificationService.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    public Qualification toUpdate(Integer id) {
        return qualificationService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/qualification/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Qualification qualification) {
        Result result = new Result();
        int i = qualificationService.updateByPrimaryKeySelective(qualification);
        if (i > 0) {
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }


}
