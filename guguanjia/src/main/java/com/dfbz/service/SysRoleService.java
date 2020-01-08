package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 19:33
 * @desciption
 */
public interface SysRoleService extends BaseService<SysRole> {
    List<SysRole> selectAllRole();

    PageInfo<SysRole> selectPage(Map<String, Object> params);

    int deleteBatch(Long rid, Long[] uids);

    int insertBatch(long rid, List<Long> uids);
}
