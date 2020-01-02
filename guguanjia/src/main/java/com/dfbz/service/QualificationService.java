package com.dfbz.service;

import com.dfbz.entity.Qualification;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/25 16:23
 * @desciption
 */
public interface QualificationService extends BaseService<Qualification> {
    PageInfo<Qualification> selectByCondition(Map<String, Object> params);
}
