package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysOffice;
import com.dfbz.entity.Waste;
import com.dfbz.entity.WasteType;
import com.dfbz.mapper.SysOfficeMapper;
import com.dfbz.service.SysOfficeService;
import com.dfbz.service.WasteService;
import com.dfbz.service.WasteTypeService;
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
 * @date 2019/12/26 18:01
 * @desciption
 */
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {
    @Autowired
    SysOfficeService sysOfficeService;

    @Autowired
    WasteTypeService wasteTypeService;
    @Autowired
    WasteService wasteService;

    @RequestMapping("list")
    public List<SysOffice> list() {
        return sysOfficeService.selectAll();
    }

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/office/office");
    }

    @RequestMapping("toList")
    private PageInfo<SysOffice> toList(@RequestBody Map<String, Object> params) {
        return sysOfficeService.selectPage(params);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/office/update");
    }

    @RequestMapping("toUpdate")
    public SysOffice toUpdate(Long oid) {
        return sysOfficeService.selectByOid(oid);
    }

    @RequestMapping("selectWasteType")
    public List<WasteType> selectWasteType() {
        return wasteTypeService.selectAll();
    }

    @RequestMapping("selectWaste")
    public List<Waste> selectWaste(Long parentId) {
        Waste waste = new Waste();
        waste.setParentId(parentId);
        return wasteService.select(waste);
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysOffice sysOffice){
        int update = sysOfficeService.update(sysOffice);
        Result result = new Result();
        if(update>0){
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }

}
