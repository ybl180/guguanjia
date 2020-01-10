package com.dfbz.mapper;

import com.dfbz.entity.SysOffice;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysOfficeMapper extends Mapper<SysOffice> {

    @SelectProvider(type = SysOfficeProvider.class, method = "selectByCondition")
    List<SysOffice> selectByCondition(Map<String, Object> params);

    //关联映射
    @Select("SELECT " +
            "  so.*, sa.NAME areaName  " +
            "FROM " +
            "  sys_office so, " +
            "  sys_area sa  " +
            "WHERE " +
            "  so.area_id = sa.id  " +
            "  AND so.del_flag = 0  " +
            "  AND so.id = #{oid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "areaName", property = "areaName"),
            @Result(column = "id", property = "wastes", many = @Many(select = "com.dfbz.mapper.WasteMapper.selectByOid"))
    })
    SysOffice selectByOid(long oid);

    @Delete("delete from office_waste where office_id=#{id}")
    int deleteOfficeWaste(long id);

    @InsertProvider(type = SysOfficeProvider.class, method = "insertBathOfficeWaste")
    int insertBathOfficeWaste(@Param("id") long id, @Param("wasteIds") long[] wasteIds);

    @Select("select so.* " +
            "from " +
            "sys_office so,sys_role_office sro " +
            "where " +
            "so.id=sro.office_id " +
            "and " +
            "sro.role_id=#{rid}")
    List<SysOffice> selectByRid(Long rid);


    @Select("select so.*  " +
            "from " +
            "sys_office so " +
            "left join  " +
            "sys_user su " +
            "on  " +
            "su.office_id=so.id " +
            "where " +
            "su.id=#{uid}")
    SysOffice selectByUid(Long uid);
}