package com.dfbz.service;

import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageInfo;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 16:43
 * @desciption
 */
public interface AppVersionService extends BaseService<AppVersion> {
    PageInfo<AppVersion> selectPage(Integer pageNum, Integer pageSize);
}
