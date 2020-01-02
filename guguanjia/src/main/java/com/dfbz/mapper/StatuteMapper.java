package com.dfbz.mapper;

import com.dfbz.entity.Statute;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface StatuteMapper extends Mapper<Statute> {

    @SelectProvider(type = StatuteProvide.class,method ="selectByCondition" )
    List<Statute> selectByCondition(Map<String, Object> params);

}