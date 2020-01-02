package com.dfbz.service.Impl;

import com.dfbz.entity.Demand;
import com.dfbz.service.DemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/24 16:20
 * @desciption
 */
@Service
@Transactional
public class DemandServiceImpl extends BaseServiceImpl<Demand> implements DemandService {

    @Override
    public PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize) {
        //分页拦截器 自动添加limit X,X
        PageHelper.startPage(pageNum, pageSize);
        List<Demand> demands = mapper.selectAll();
        return new PageInfo<>(demands);
    }
}
