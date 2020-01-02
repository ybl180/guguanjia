package com.dfbz.service;

import com.dfbz.entity.Demand;
import com.github.pagehelper.PageInfo;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/24 16:18
 * @desciption
 */
public interface DemandService extends BaseService<Demand> {

    PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize);
}
