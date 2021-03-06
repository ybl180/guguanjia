package com.dfbz.mapper;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 17:04
 * @desciption
 */
public class SysUserProvider {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                " sr.NAME roleName, " +
                " so.NAME OfficeName, " +
                " su.*  " +
                "FROM " +
                " sys_user su " +
                " LEFT JOIN sys_office so ON su.office_id = so.id " +
                " LEFT JOIN sys_user_role sur ON su.id = sur.user_id " +
                " LEFT JOIN sys_role sr ON sr.id = sur.role_id  " +
                "WHERE " +
                " su.del_flag = 0 ");
        if (params.containsKey("userNo") && !StringUtils.isEmpty(params.get("userNo"))) {
            sb.append(" AND su.`no` = #{userNo} ");
        }
        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            sb.append(" AND su.NAME LIKE concat( '%', #{name}, '%' )  ");
        }
        if (params.containsKey("officeId") && !StringUtils.isEmpty(params.get("officeId")) && (int) params.get("officeId") != 0) {
            sb.append(" AND so.id = #{officeId}  ");
        }

        if (params.containsKey("roleIds") && !StringUtils.isEmpty(params.get("roleIds"))) {
            List<Long> roleIds = (List<Long>) params.get("roleIds");
            if (roleIds.size() > 0) {
                sb.append(" AND sr.id IN ( ");
                for (int i = 0; i < roleIds.size(); i++) {
                    sb.append("#{roleIds[" + i + "]},");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            }
        }

        return sb.toString();
    }
}
