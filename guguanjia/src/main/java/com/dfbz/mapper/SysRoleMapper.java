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


    //批量删除sys_role_resource中的信息
    @DeleteProvider(type = SysRoleProvider.class, method = "deleteBatchRoleResource")
    int deleteBatchRoleResource(@Param("rid") Long rid);

    //批量添加sys_role_resource中的信息
    @InsertProvider(type = SysRoleProvider.class, method = "insertBatchRoleResource")
    int insertBatchRoleResource(@Param("rid") Long rid, @Param("resIds") List<Long> resIds);

    //批量删除sys_role_resource中的信息
    @DeleteProvider(type = SysRoleProvider.class, method = "deleteBatchRoleOffice")
    int deleteBatchRoleOffice(@Param("rid") Long rid);

    //批量添加sys_role_resource中的信息
    @InsertProvider(type = SysRoleProvider.class, method = "insertBatchRoleOffice")
    int insertBatchRoleOffice(@Param("rid") Long rid, @Param("officeIds") List<Long> officeIds);

    //逻辑删除角色，del_flag=1
    @Update("update sys_role set del_flag=1 where id=#{rid}")
    int deleteRole(Long rid);

    //删除关联role的信息  中间表
    @Delete("delete from  sys_role_office where role_id=#{rid}")
    int deleteRelevanceRoleOffice(Long rid);
    @Delete("delete from  sys_role_resource where role_id=#{rid}")
    int deleteRelevanceRoleResource(Long rid);
    @Delete("delete from  sys_user_role  where role_id=#{rid}")
    int deleteRelevanceRoleUser(Long rid);


    //新增角色
    @InsertProvider(type = SysRoleProvider.class, method = "saveRole")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")//能够为对象生成自增的主键值
    int saveRole(@Param("role") SysRole role);
}