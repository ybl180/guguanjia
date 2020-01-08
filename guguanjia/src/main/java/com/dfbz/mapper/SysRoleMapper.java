package com.dfbz.mapper;

import com.dfbz.entity.SysRole;
import com.jhlabs.math.RidgedFBM;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {

    //关联office查询出所有的role
    @Select("SELECT " +
            " so.NAME officeName, " +
            " sr.*  " +
            "FROM " +
            " sys_role sr " +
            " LEFT JOIN sys_role_office sro ON sr.id = sro.role_id " +
            " LEFT JOIN sys_office so ON so.id = sro.office_id")
    List<SysRole> selectAllRole();

    //按条件分页查询
    @SelectProvider(type = SysRoleProvider.class, method = "selectByCondition")
    List<SysRole> selectByCondition(Map<String, Object> params);

    //批量删除role中的user
    @DeleteProvider(type = SysRoleProvider.class, method = "deleteBatch")
    int deleteBatch(@Param("rid") Long rid, @Param("uids") Long[] uids);

    @InsertProvider(type = SysRoleProvider.class, method = "insertBatch")
    int insertBatch(@Param("rid") long rid, @Param("uids") List<Long> uids);
}