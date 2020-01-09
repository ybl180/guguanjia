package com.dfbz.mapper;

import com.dfbz.entity.SysResource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {
    @Select("select sre.* from " +
            "sys_role_resource srr, sys_resource sre " +
            "where  " +
            "sre.id=srr.resource_id " +
            "and " +
            "srr.role_id=#{rid}")
    public List<SysResource> selectByRid(Long rid);
}