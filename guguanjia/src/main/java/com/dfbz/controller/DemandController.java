package com.dfbz.controller;

import com.dfbz.entity.Demand;
import com.dfbz.entity.Result;
import com.dfbz.service.DemandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/24 16:22
 * @desciption
 */
@RestController
@RequestMapping("manager/demand")
public class DemandController {
    @Autowired
    DemandService demandService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/demand/index");
    }

    @RequestMapping("toIndex")
    public PageInfo<Demand> toIndex(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        return demandService.selectPage(pageNum, pageSize);
    }

    @RequestMapping("toUpdate")
    public Demand toUpdate(Integer id) {
        return demandService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/demand/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Demand demand) {
        Result result = new Result();
        int i = demandService.updateByPrimaryKeySelective(demand);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("detail")
    public ModelAndView toDetail() {
        return new ModelAndView("/demand/detail");
    }

}
