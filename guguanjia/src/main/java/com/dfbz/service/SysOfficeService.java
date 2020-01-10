package com.dfbz.service;

import com.dfbz.entity.SysOffice;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/2 17:30
 * @desciption
 */
public interface SysOfficeService extends BaseService<SysOffice> {
    PageInfo<SysOffice> selectPage(Map<String, Object> params);

    //通过office的id查询office和关联waste areaName
    SysOffice selectByOid(Long oid);

    int update(SysOffice sysOffice);

    List<SysOffice> selectByRid(Long rid);

    SysOffice selectByUid(Long uid);
}
