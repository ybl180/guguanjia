package com.dfbz.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/26 16:49
 * @desciption
 */
public class ExamineProvider {

    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ex.*, su.NAME examine_user_name, so.NAME examine_user_office FROM examine ex, sys_user su, " +
                "sys_office so WHERE ex.del_flag = 0 AND ex.examine_user_id = su.id  AND su.office_id = so.id ");
        if (map.containsKey("type") && !StringUtils.isEmpty(map.get("type"))) {
            sb.append(" and ex.type=#{type} ");
        }
        if (map.containsKey("examineUserName") && !StringUtils.isEmpty(map.get("examineUserName"))) {
            sb.append(" and su.name like concat('%',#{examineUserName},'%' )");
        }
        if (map.containsKey("examineUserOfficeId") && !StringUtils.isEmpty(map.get("examineUserOfficeId"))
                &&(int)map.get("examineUserOfficeId")!=0) {
            sb.append(" and so.id=#{examineUserOfficeId} ");
        }
        return sb.toString();
    }
}
