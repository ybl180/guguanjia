package com.dfbz.mapper;

import com.dfbz.entity.Transfer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransferMapper extends Mapper<Transfer> {

    @Select("SELECT " +
            " tr.*, " +
            " su.name, " +
            " su.phone  " +
            "FROM " +
            " transfer tr, " +
            " sys_user su  " +
            "WHERE " +
            " tr.oprate_user_id = su.id  " +
            " AND tr.del_flag = 0  " +
            " AND tr.work_order_id = #{oid}  " +
            "ORDER BY " +
            " create_date DESC")
    List<Transfer> selectByOid(Long oid);
}