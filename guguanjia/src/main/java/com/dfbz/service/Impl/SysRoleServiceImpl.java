package com.dfbz.service.Impl;

import com.alibaba.excel.util.StringUtils;
import com.dfbz.entity.SysRole;
import com.dfbz.mapper.SysRoleMapper;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 19:34
 * @desciption
 */
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectAllRole() {
        return sysRoleMapper.selectAllRole();
    }

    @Override
    public PageInfo<SysRole> selectPage(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysRole> list = sysRoleMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteBatch(Long rid, Long[] uids) {
        return sysRoleMapper.deleteBatch(rid, uids);
    }

    @Override
    public int insertBatch(long rid, List<Long> uids) {
        return sysRoleMapper.insertBatch(rid, uids);
    }


}
