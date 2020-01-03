package com.dfbz.mapper;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {
    @InsertProvider(type = SysAreaProvider.class, method = "insertBatch")
    int insertBatch(@Param("areas") List<SysArea> areas);

    @SelectProvider(type=SysAreaProvider.class, method = "selectByCondition")
    List<SysArea> selectByCondition(Map<String,Object> params);
}