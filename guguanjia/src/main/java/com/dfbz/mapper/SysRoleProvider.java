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

    //批量删除sys_role_resource中的信息
    public String deleteBatchRoleResource(@Param("rid") Long rid) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete " +
                "from sys_role_resource  " +
                "where " +
                "role_id=#{rid}");
        return sb.toString();
    }

    //批量插入sys_role_resource中的信息
    public String insertBatchRoleResource(@Param("rid") Long rid, @Param("resIds") List<Long> resIds) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `guguanjia`.`sys_role_resource` ( `role_id`, `resource_id`, `create_by`, `create_date`," +
                " `update_by`, `update_date`, `del_flag` ) VALUES ");
        for (int i = 0; i < resIds.size(); i++) {
            sb.append(" ( #{rid}, #{resIds[" + i + "]}, NULL, now(), NULL, now(), '0' ),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //逻辑删除关联role的信息  中间表
    public String deleteRelevanceRole(Long rid) {
        StringBuilder sb = new StringBuilder();
        sb.append("update  " +
                "sys_role_office sro,sys_role_resource srr,sys_user_role sur " +
                "set " +
                "sro.del_flag=1,srr.del_flag=1,sur.del_flag=1 " +
                "where " +
                "sro.role_id=#{rid} " +
                "and " +
                "srr.role_id=#{rid} " +
                "and " +
                "sur.role_id=#{rid}");
        return sb.toString();
    }


}
