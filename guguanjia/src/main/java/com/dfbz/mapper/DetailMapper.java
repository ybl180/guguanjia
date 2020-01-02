package com.dfbz.mapper;

import com.dfbz.entity.Detail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DetailMapper extends Mapper<Detail> {
    @Select("SELECT " +
            " de.*, " +
            " wt.NAME wasteTypeName, " +
            " wt.CODE wasteTypeCode, " +
            " wa.CODE wasteCode  " +
            "FROM " +
            " detail de, " +
            " waste wa, " +
            " waste_type wt  " +
            "WHERE " +
            " de.waste_id = wa.id  " +
            " AND de.waste_type_id = wt.id  " +
            " AND de.del_flag = 0  " +
            " AND de.work_order_id = #{uid}")
    List<Detail> selectByOid(Long uid);
}