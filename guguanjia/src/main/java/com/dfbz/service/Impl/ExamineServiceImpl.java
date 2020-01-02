package com.dfbz.service.Impl;

import com.dfbz.entity.Examine;
import com.dfbz.mapper.ExamineMapper;
import com.dfbz.service.ExamineService;
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
 * @date 2019/12/26 17:01
 * @desciption
 */
@Service
@Transactional
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {
    @Autowired
    ExamineMapper examineMapper;

    @Override
    public PageInfo<Examine> selectPage(Map<String, Object> map) {
        if (!map.containsKey("pageNum") || StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (!map.containsKey("pageSize") || StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((int) map.get("pageNum"), (int) map.get("pageSize"));
        List<Examine> list = examineMapper.selectByCondition(map);
        return new PageInfo<>(list);
    }
}
