package com.dfbz.service;

import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/27 17:01
 * @desciption
 */
public interface WorkOrderService extends BaseService<WorkOrder> {
    PageInfo<WorkOrder> selectPage(Map<String, Object> params);

    //根据workOrder的id查询一个订单详情
    Map<String, Object> selectByOid(Long oid);
}
