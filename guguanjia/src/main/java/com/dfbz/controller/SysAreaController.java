package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysArea;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 16:06
 * @desciption
 */
@RestController
@RequestMapping("manager/area")
public class SysAreaController {
    @Autowired
    SysAreaService sysAreaService;

    @RequestMapping("")
    public ModelAndView toIndex() {
        return new ModelAndView("/area/area");
    }

    /**
     * Excel下载操作:
     * 1.设置响应头
     * 2.设置文件乱码处理
     * 3.获取响应数据流
     * 4.写出到页面
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=sysArea.xls");
        OutputStream outputStream = response.getOutputStream();
        outputStream = sysAreaService.writeExcel(outputStream);//响应流数据已经有文件信息
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile upFile) throws IOException {
        Result result = new Result();
        int i = sysAreaService.readExcel(upFile.getInputStream());
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("导入数据成功");
        }
        return result;
    }

    @RequestMapping("toList")
    public PageInfo<SysArea> toList(@RequestBody Map<String, Object> params) {
        return sysAreaService.selectByCondition(params);
    }

    @RequestMapping("selectAll")
    public List<SysArea> selectAll() {
        return sysAreaService.selectAll();
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(Integer id) {
        return sysAreaService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/area/save");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea area) {
        Result result = new Result();
        int i = sysAreaService.updateArea(area);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("awesome")
    public ModelAndView awesome() {
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("selectParent")
    public ModelAndView selectHtml() {
        return new ModelAndView("/area/select");
    }

}
