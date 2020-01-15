package com.dfbz.service;

import com.dfbz.entity.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/14 14:57
 * @desciption
 */
public interface SysLogService extends BaseService<SysLog> {
    PageInfo<SysLog> selectByCondition(Map<String, Object> params);
}
