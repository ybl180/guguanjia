package com.dfbz.mapper;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
}