package com.dfbz.mapper;

import com.dfbz.entity.Qualification;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface QualificationMapper extends Mapper<Qualification> {

    @SelectProvider(value = QualificationProvide.class, method = "selectByCondition")
    List<Qualification> selectByCondition(Map<String, Object> params);

}