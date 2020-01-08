package com.dfbz.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/8 13:39
 * @desciption
 */
public class SysRoleProvider {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sr.*,so.name officeName " +
                "from " +
                "sys_role sr,sys_office so " +
                "where  " +
                "sr.del_flag=0 " +
                "and " +
                "sr.office_id=so.id ");
        if (params.containsKey("dataScope") && !StringUtils.isEmpty(params.get("dataScope"))) {
            sb.append(" and sr.data_scope=#{dataScope} ");
        }
        if (params.containsKey("officeId") && !StringUtils.isEmpty(params.get("officeId"))) {
            sb.append(" and so.id=#{officeId} ");
        }
        if (params.containsKey("remarks") && !StringUtils.isEmpty(params.get("remarks"))) {
            sb.append(" and sr.remarks like concat('%',#{remarks},'%') ");
        }
        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            sb.append(" and sr.name like concat('%',#{name},'%') ");
        }
        return sb.toString();
    }


    public String deleteBatch(@Param("rid") Long rid, @Param("uids") Long[] uids) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " +
                "sys_user_role " +
                "where " +
                "role_id=#{rid} " +
                "and " +
                "user_id in (");
        for (int i = 0; i < uids.length; i++) {
            sb.append("#{uids[" + i + "]},");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public String insertBatch(@Param("rid") long rid, @Param("uids") List<Long> uids) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_user_role`( `role_id`, `user_id`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `del_flag`) VALUES ");
        for (int i = 0; i < uids.size(); i++) {
            sb.append("(#{rid},#{uids[" + i + "]},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
