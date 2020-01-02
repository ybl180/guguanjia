package com.dfbz.service;

import com.dfbz.entity.Statute;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/30 19:54
 * @desciption
 */
public interface StatuteService extends BaseService<Statute> {
    PageInfo<Statute> selectByCondition(Map<String, Object> params);
}
