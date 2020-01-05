package com.dfbz.mapper;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {
    @InsertProvider(type = SysAreaProvider.class, method = "insertBatch")
    int insertBatch(@Param("areas") List<SysArea> areas);

    @SelectProvider(type = SysAreaProvider.class, method = "selectByCondition")
    List<SysArea> selectByCondition(Map<String, Object> params);


    //根据父区域更新所有的子区域的parentIds
    @Update("update sys_area set " +
            "parent_ids=replace(parent_ids,#{oldParentId},#{parentId}) " +
            "where " +
            "parent_ids like concat('%',#{id},'%')")
    int updateParentIds(SysArea area);
}