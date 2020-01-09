package com.dfbz.service;

import com.dfbz.entity.SysResource;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/9 15:42
 * @desciption
 */
public interface SysResourceService extends BaseService<SysResource> {
    List<SysResource> selectByRid(Long rid);
}
