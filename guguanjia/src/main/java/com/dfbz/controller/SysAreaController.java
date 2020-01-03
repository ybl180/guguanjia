package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
}
