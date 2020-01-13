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


    @Select("SELECT DISTINCT " +
            " sre.id, " +
            " sre.`name`, " +
            " sre.common, " +
            " sre.icon, " +
            " sre.sort, " +
            " sre.parent_id, " +
            " sre.type, " +
            " sre.url, " +
            " sre.description, " +
            " sre.`status`, " +
            " sre.parent_ids, " +
            " sre.create_date, " +
            " sre.update_date, " +
            " sre.create_by, " +
            " sre.update_by, " +
            " sre.del_flag, " +
            " sre.permission_str  " +
            "FROM " +
            " sys_user_role sur, " +
            " sys_role sro, " +
            " sys_role_resource srr, " +
            " sys_resource sre  " +
            "WHERE " +
            " sur.user_id = #{uid}  " +
            " AND sur.role_id = sro.id  " +
            " AND sro.id = srr.role_id  " +
            " AND srr.resource_id = sre.id")
    List<SysResource> selectByUid(long uid);
}