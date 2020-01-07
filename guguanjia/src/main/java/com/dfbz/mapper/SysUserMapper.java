package com.dfbz.mapper;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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

    @SelectProvider(type = SysUserProvider.class,method ="selectByCondition")
    List<SysUser> selectByCondition(Map<String,Object> params);
}