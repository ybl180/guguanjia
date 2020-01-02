package com.dfbz.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/27 16:51
 * @desciption
 */
public class WorkOrderProvider {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                " wo.*, " +
                " cu.NAME createUserName, " +
                " co.NAME createUserOfficeName, " +
                " tu.NAME transportUserName, " +
                " ru.NAME recipientUserName " +
                "FROM " +
                " work_order wo " +
                " LEFT JOIN sys_user cu  " +
                " ON wo.create_user_id = cu.id " +
                " LEFT JOIN sys_user tu  " +
                " ON wo.transport_user_id = tu.id " +
                " LEFT JOIN sys_user ru  " +
                " ON wo.recipient_user_id = ru.id " +
                " LEFT JOIN sys_office co  " +
                " ON cu.office_id = co.id " +
                " LEFT JOIN sys_office tro  " +
                " ON tu.office_id = tro.id " +
                " LEFT JOIN sys_office ro  " +
                " ON ru.office_id = ro.id  " +
                "WHERE " +
                " wo.del_flag = 0 ");
        if (params.containsKey("status") && !StringUtils.isEmpty(params.get("status"))) {
            sb.append(" and wo.status=#{status}");
        }
        if (params.containsKey("start") && !StringUtils.isEmpty(params.get("start"))) {
            sb.append("AND wo.create_date >= #{start} ");
        }
        if (params.containsKey("end") && !StringUtils.isEmpty(params.get("end"))) {
            sb.append(" AND wo.create_date <= #{end}");
        }
        if (params.containsKey("officeId") && !StringUtils.isEmpty(params.get("officeId"))&&!params.get("officeId").equals(0)) {
            sb.append(" AND (co.id =#{officeId} OR tro.id = #{officeId} OR ro.id =  #{officeId})");
        }
        return sb.toString();
    }
}
