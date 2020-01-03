package com.dfbz.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dfbz.mapper.SysAreaMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 17:02
 * @desciption
 */
public class SysAreaListener extends AnalysisEventListener<SysArea> {

    private SysAreaMapper mapper;
    private List<SysArea> list = new ArrayList<>();

    public SysAreaListener() {
    }

    public SysAreaListener(SysAreaMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void invoke(SysArea sysArea, AnalysisContext analysisContext) {
        list.add(sysArea);
        if (list.size() == 10) {//方便内存回收
            mapper.insertBatch(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (list.size() > 0) {
            mapper.insertBatch(list);
        }
    }
}
