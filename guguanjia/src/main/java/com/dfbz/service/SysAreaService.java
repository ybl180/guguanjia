package com.dfbz.service;

import com.dfbz.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 16:04
 * @desciption
 */
public interface SysAreaService extends BaseService<SysArea>{
    OutputStream writeExcel(OutputStream outputStream);

    // 将传入的excel文件的组成的inputStream流读取，转换成java对象，并且进行批量插入到数据库
    int readExcel(InputStream inputStream);

    PageInfo<SysArea> selectByCondition(Map<String, Object> params);

    int updateArea(SysArea area);
}
