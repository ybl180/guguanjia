package com.dfbz.service;

import com.dfbz.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/26 17:00
 * @desciption
 */
public interface ExamineService extends BaseService<Examine> {
    PageInfo<Examine> selectPage(Map<String, Object> map);
}
