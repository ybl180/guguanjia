package com.dfbz.service.Impl;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 16:58
 * @desciption
 */
@Service
@Transactional
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion> implements AppVersionService {
    @Override
    public PageInfo<AppVersion> selectPage(Integer pageNum, Integer pageSize) {
        //开启分页拦截，分页插件会自动早最近一个SQL执行前自动添加分页 limit X,X
        PageHelper.startPage(pageNum, pageSize);

        //替换成通用mapper的Example实现    主要用于动态条件、排序等查询
//        Example example = new Example(AppVersion.class);
//        Example.Criteria criteria = example.createCriteria();//语句构造对象select *  from app_version
//        criteria.andEqualTo("delFlag", "0");
//        List<AppVersion> list = mapper.selectByExample(example);

        //替换select(obj)是一个动态查询方法
        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        List<AppVersion> list = mapper.select(appVersion);

//        List<AppVersion> list = mapper.selectAll();
        return new PageInfo<>(list);
    }
}

