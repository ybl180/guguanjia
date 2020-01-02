package com.dfbz.controller;

import com.dfbz.entity.WorkOrder;
import com.dfbz.service.WorkOrderService;
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
 * @date 2019/12/27 17:16
 * @desciption
 */
@RestController
@RequestMapping("manager/admin")
public class WorkOrderController {
    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("work")
    public ModelAndView toHtml() {
        return new ModelAndView("/work/admin/work");
    }

    @RequestMapping("toList")
    public PageInfo<WorkOrder> toList(@RequestBody Map<String, Object> params) {
        return workOrderService.selectPage(params);
    }

    @RequestMapping("print")
    public ModelAndView toPrint() {
        return new ModelAndView("/work/print");
    }

    @RequestMapping("selectByOid")
    public Map<String, Object> selectByOid(long oid) {
        return workOrderService.selectByOid(oid);
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail() {
        return new ModelAndView("/work/work-detail");
    }

}
