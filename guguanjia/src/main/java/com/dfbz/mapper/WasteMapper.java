package com.dfbz.mapper;

import com.dfbz.entity.Waste;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WasteMapper extends Mapper<Waste> {

    //根据office的id查询waste和waste_type的信息
    @Select("SELECT " +
            "  wa.*, " +
            "  wt.code wasteTypeCode " +
            "FROM " +
            "  sys_office so " +
            "  LEFT JOIN office_waste ow " +
            "ON so.id = ow.office_id " +
            "  LEFT JOIN waste wa " +
            "ON wa.id = ow.waste_id " +
            "  LEFT JOIN waste_type wt " +
            "ON wt.id = wa.parent_id  " +
            "WHERE " +
            "  so.id =#{oid}")
    List<Waste> selectByOid(Long oid);
}