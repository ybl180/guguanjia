package com.dfbz.service.Impl;

import com.alibaba.excel.util.StringUtils;
import com.dfbz.entity.SysUser;
import com.dfbz.mapper.SysUserMapper;
import com.dfbz.service.SysUserService;
import com.dfbz.utils.EncryptUtils;
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
 * @date 2020/1/7 14:34
 * @desciption
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser checkSysUser(SysUser sysUser) {
        sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(sysUser.getPassword()) + sysUser.getUsername()));
        List<SysUser> select = sysUserMapper.select(sysUser);
        if (select.size() > 0) {
            return select.get(0);
        }
        return null;
    }

    @Override
    public PageInfo<SysUser> selectPage(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysUser> list = sysUserMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<SysUser> selectByRid(Long rid) {
        return sysUserMapper.selectByRid(rid);
    }

    @Override
    public List<SysUser> selectNoRole(Long rid, Long oid) {
        return sysUserMapper.selectNoRole(rid, oid);
    }
}
