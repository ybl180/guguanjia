package com.dfbz.mapper;

import com.dfbz.entity.SysRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {

    @Select("SELECT " +
            " so.NAME officeName, " +
            " sr.*  " +
            "FROM " +
            " sys_role sr " +
            " LEFT JOIN sys_role_office sro ON sr.id = sro.role_id " +
            " LEFT JOIN sys_office so ON so.id = sro.office_id")
    List<SysRole> selectAllRole(); 
}