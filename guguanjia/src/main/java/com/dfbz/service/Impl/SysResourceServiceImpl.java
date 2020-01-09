package com.dfbz.service.Impl;

import com.dfbz.entity.SysResource;
import com.dfbz.mapper.SysResourceMapper;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/9 15:43
 * @desciption
 */
@Service
@Transactional
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {
    @Autowired
    SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> selectByRid(Long rid) {
        return sysResourceMapper.selectByRid(rid);
    }
}
