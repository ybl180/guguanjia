package com.dfbz.controller;

import com.dfbz.entity.SysOffice;
import com.dfbz.mapper.SysOfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/26 18:01
 * @desciption
 */
@RestController
@RequestMapping("manager/sys_office")
public class SysOfficeController {
    @Autowired
    SysOfficeMapper mapper;

    @RequestMapping("list")
    public List<SysOffice> list() {
        return mapper.selectAll();
    }
}
