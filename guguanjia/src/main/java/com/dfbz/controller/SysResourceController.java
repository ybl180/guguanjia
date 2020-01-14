package com.dfbz.controller;

import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/9 15:45
 * @desciption
 */
@RestController
@RequestMapping("manager/menu")
public class SysResourceController {
    @Autowired
    SysResourceService sysResourceService;

    @RequestMapping("list")
    public List<SysResource> list() {
        return sysResourceService.selectAll();
    }

    @RequestMapping("selectByRid")
    public List<SysResource> selectByRid(Long rid) {
        return sysResourceService.selectByRid(rid);
    }

    @RequestMapping("selectByUid")
    public List<SysResource> selectByUid(Long uid, HttpSession session) {
        List<SysResource> responses = sysResourceService.selectByUid(uid);
        session.setAttribute("resources", responses);
        return responses;
    }

}
