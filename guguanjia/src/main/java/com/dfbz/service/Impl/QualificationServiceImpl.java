package com.dfbz.service.Impl;

import com.dfbz.entity.Qualification;
import com.dfbz.mapper.QualificationMapper;
import com.dfbz.service.QualificationService;
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
 * @date 2019/12/25 16:24
 * @desciption
 */
@Service
@Transactional
public class QualificationServiceImpl extends BaseServiceImpl<Qualification> implements QualificationService {
    @Autowired
    QualificationMapper qualificationMapper;

    @Override
    public PageInfo<Qualification> selectByCondition(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty("pageNum")) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty("pageSize")) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<Qualification> list = qualificationMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }
}
