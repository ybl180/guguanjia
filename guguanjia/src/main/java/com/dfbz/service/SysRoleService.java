package com.dfbz.service;

import com.dfbz.entity.SysRole;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 19:33
 * @desciption
 */
public interface SysRoleService extends BaseService<SysRole> {
    List<SysRole> selectAllRole();
}
