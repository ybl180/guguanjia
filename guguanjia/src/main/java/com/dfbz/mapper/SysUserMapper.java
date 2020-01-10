package com.dfbz.mapper;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {
    @Select("SELECT " +
            "su.*," +
            "so.NAME officeName," +
            "so.id officeId " +
            "FROM " +
            "sys_user su," +
            "sys_office so " +
            "WHERE " +
            "su.office_id = so.id " +
            "AND su.id = #{uid}")
//    @Results({
//            @Result(property = "id",column = "su.id"),
//            @Result(property = "sysOffice.id",column = "officeId"),
//            @Result(property = "sysOffice.name",column = "officeName")
//    })
    SysUser selectById(Long uid);

    @SelectProvider(type = SysUserProvider.class, method = "selectByCondition")
    List<SysUser> selectByCondition(Map<String, Object> params);


    //根据角色id查询用户信息
    @Select("select su.*,sr.name roleName  " +
            "from " +
            "sys_role sr,sys_user_role sur,sys_user su " +
            "where " +
            "su.del_flag=0 " +
            "and " +
            "sr.id=sur.role_id " +
            "and " +
            "su.id=sur.user_id " +
            "and " +
            "sr.id=#{rid}")
    List<SysUser> selectByRid(Long rid);


    //根据公司id和角色id查询出未分配该角色的人员
    @Select("SELECT " +
            " *  " +
            "FROM " +
            " sys_user  " +
            "WHERE " +
            " office_id = #{oid}  " +
            " AND id NOT IN ( " +
            " SELECT " +
            "  su.id  " +
            " FROM " +
            "  sys_role sr, " +
            "  sys_user su, " +
            "  sys_user_role sur  " +
            " WHERE " +
            "  sr.id = sur.role_id  " +
            "  AND su.id = sur.user_id  " +
            "  AND sr.id = #{rid}  " +
            " )")
    List<SysUser> selectNoRole(@Param("rid") Long rid, @Param("oid") Long oid);
}