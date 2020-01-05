package com.dfbz.mapper;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 17:07
 * @desciption
 */
public class SysAreaProvider {
    public String insertBatch(@Param("areas") List<SysArea> areas) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO `guguanjia`.`sys_area`( `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`," +
                " `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`) VALUES ");
        for (int i = 0; i < areas.size(); i++) {
            sb.append(" (");
            sb.append(" #{areas[" + i + "].parentId}, #{areas[" + i + "].parentIds}, #{areas[" + i + "].code}, #{areas[" + i + "].name}," +
                    " #{areas[" + i + "].type}, #{areas[" + i + "].createBy}, #{areas[" + i + "].createDate}, #{areas[" + i + "].updateBy}," +
                    " #{areas[" + i + "].updateDate}, #{areas[" + i + "].remarks}, #{areas[" + i + "].delFlag}, #{areas[" + i + "].icon}");
            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //根据父区域id查找所有区域
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select " +
                "sub.* , " +
                "parent.name parentName " +
                "from  " +
                "sys_area sub " +
                "left join " +
                "sys_area parent " +
                "on " +
                "sub.parent_id=parent.id " +
                "where " +
                " (sub.parent_ids like CONCAT('%',#{pid},'%')" +
                "or sub.id=#{pid})");
        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            sb.append(" and sub.name like CONCAT('%',#{name},'%')");
        }
        return sb.toString();
    }
}
