package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.Statute;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/30 20:13
 * @desciption
 */
@RestController
@RequestMapping("manager/statute")
public class StatuteController {
    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("toList")
    public PageInfo<Statute> toList(@RequestBody Map<String, Object> params) {
        return statuteService.selectByCondition(params);
    }

    @RequestMapping("insert")
    public Result toInsert(@RequestBody Statute statute) {
        Result result = new Result();
        statute.setCreateDate(new Date());
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        int i = statuteService.insertSelective(statute);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        }
        return result;
    }

    @RequestMapping("toUpdate")
    public Statute toUpdate(Integer id) {
        return statuteService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/statute/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Statute statute) {
        Result result = new Result();
        int i = statuteService.updateByPrimaryKeySelective(statute);
        if (i > 0) {
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }

}
