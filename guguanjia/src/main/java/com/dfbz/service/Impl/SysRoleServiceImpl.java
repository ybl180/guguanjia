package com.dfbz.service.Impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.excel.util.StringUtils;
import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysRole;
import com.dfbz.mapper.SysRoleMapper;
import com.dfbz.service.SysRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
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
        return sysRoleMapper.selectAll();
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

    @Override
    public int update(Map<String, Object> params) {
        int result = 0;
        if (params.containsKey("role") && !StringUtils.isEmpty(params.get("role"))) {

            //将LinkedHashMap转换成javaObject
            ObjectMapper objectMapper = new ObjectMapper();
            SysRole role = objectMapper.convertValue(params.get("role"), SysRole.class);
            //更新SysRole数据库表数据
            result += sysRoleMapper.updateByPrimaryKeySelective(role);

            List<Long> resIds = objectMapper.convertValue(params.get("resIds"), List.class);
            //更新sys_role_resource中间表数据，先批量删，再批量添加
            result += sysRoleMapper.deleteBatchRoleResource(role.getId());
            if (resIds.size() > 0) {
                result += sysRoleMapper.insertBatchRoleResource(role.getId(), resIds);
            }

            //更新sys_role_office中间表数据
            List<Long> officeIds = objectMapper.convertValue(params.get("officeIds"), List.class);
            result += sysRoleMapper.deleteBatchRoleOffice(role.getId());
            if (officeIds.size() > 0) {
                result += sysRoleMapper.insertBatchRoleOffice(role.getId(), officeIds);
            }
        }
        return result;
    }

    //逻辑删除角色
    @Override
    public int deleteRole(Long rid) {
        int result = 0;
        result += sysRoleMapper.deleteRole(rid);
        //删除关联表数据
        sysRoleMapper.deleteRelevanceRoleOffice(rid);
        sysRoleMapper.deleteRelevanceRoleResource(rid);
        sysRoleMapper.RdeleteRelevanceRoleUser(rid);
        return result;
    }

    @Override
    public int saveRole(Map<String, Object> params) {
        int result = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        SysRole role = objectMapper.convertValue(params.get("role"), SysRole.class);
        result += sysRoleMapper.saveRole(role);

        //授权资源,插入sys_role_resource
        List<Long> resIds = objectMapper.convertValue(params.get("resIds"), List.class);
        if (resIds.size() > 0) {
            sysRoleMapper.insertBatchRoleResource(role.getId(), resIds);
        }
        //授权公司,插入sys_role_office
        List<Long> officeIds = objectMapper.convertValue(params.get("officeIds"), List.class);
        if (officeIds.size() > 0) {
            sysRoleMapper.insertBatchRoleOffice(role.getId(), officeIds);
        }
        return result;
    }


}
