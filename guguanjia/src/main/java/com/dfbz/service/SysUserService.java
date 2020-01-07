package com.dfbz.service;

import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 14:33
 * @desciption
 */
public interface SysUserService extends BaseService<SysUser> {
    SysUser checkSysUser(SysUser sysUser);

    PageInfo<SysUser> selectPage(Map<String, Object> params);
}
