package com.dfbz.service.Impl;

import com.dfbz.entity.Statute;
import com.dfbz.mapper.StatuteMapper;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/30 19:55
 * @desciption
 */
@Service
@Transactional
@CacheConfig(cacheNames = "statuteCache")
public class StatuteServiceImpl extends BaseServiceImpl<Statute> implements StatuteService {
    @Autowired
    StatuteMapper statuteMapper;

    //命中率：正确命中缓存的数据
    @Cacheable(key = "'StatuteServiceImpl:selectByCondition'+#params['pageNum']+#params['pageSize']+#params['type']")
    public PageInfo<Statute> selectByCondition(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<Statute> list = statuteMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }

}
