package com.dfbz.mapper;


import com.dfbz.entity.Qualification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/25 15:47
 * @desciption
 */
public class QualificationProvide {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT qu.*, uu.NAME upload_user_name, cu.NAME check_user_name, uu.office_id upload_user_office_id FROM" +
                " qualification qu LEFT JOIN sys_user uu ON qu.upload_user_id = uu.id LEFT JOIN sys_user cu ON qu.check_user_id = cu.id WHERE qu.del_flag =0 ");
        if (params.containsKey("type") && !StringUtils.isEmpty(params.get("type"))) {
            sb.append(" and qu.type=#{type}");
        }
        if (params.containsKey("check") && !StringUtils.isEmpty(params.get("check"))) {
            sb.append(" and qu.`check`=#{check}");
        }
        if (params.containsKey("start") && !StringUtils.isEmpty(params.get("start"))) {
            sb.append(" and qu.create_date>=#{start}");
        }
        if (params.containsKey("end") && !StringUtils.isEmpty(params.get("end"))) {
            sb.append(" and qu.create_date<=#{end}");
        }
        return sb.toString();
    }
}
