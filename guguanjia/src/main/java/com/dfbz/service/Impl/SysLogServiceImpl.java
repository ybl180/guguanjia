package com.dfbz.service.Impl;

import com.dfbz.entity.SysLog;
import com.dfbz.mapper.SysLogMapper;
import com.dfbz.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/14 14:58
 * @desciption
 */
@Service
@Transactional
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public PageInfo<SysLog> selectByCondition(Map<String, Object> params){
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysLog> list = sysLogMapper.selectAll();
        return new PageInfo<>(list);
    }
}
