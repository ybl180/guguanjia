package com.dfbz.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.entity.Statute;
import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysAreaListener;
import com.dfbz.mapper.SysAreaMapper;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 16:05
 * @desciption
 */
@Service
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {
    @Autowired
    SysAreaMapper sysAreaMapper;

    //根据传入的输出流对象，进行excel写入操作，并返回该输出流对象
    @Override
    public OutputStream writeExcel(OutputStream outputStream) {
        //获取excel写出对象
        ExcelWriter writer = EasyExcel.write(outputStream, SysArea.class).build();
        //获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysArea数据").build();
        List<SysArea> areaList = mapper.selectAll();
        //将数据写出到excel对应的sheet位置
        writer.write(areaList, sheet);
        writer.finish();
        return outputStream;
    }

    // 将传入的excel文件的组成的inputStream流读取，转换成java对象，并且进行批量插入到数据库
    @Override
    public int readExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader reader = EasyExcel.read(inputStream, SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);//读资源
        reader.finish();
        result += 1;
        return result;
    }

    public PageInfo<SysArea> selectByCondition(Map<String, Object> params) {

        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysArea> list = null;
        if (params.containsKey("pid") && !StringUtils.isEmpty(params.get("pid"))) {
            list = sysAreaMapper.selectByCondition(params);
        }
        return new PageInfo<>(list);
    }

    /* 1.更新区域的信息
     * 2.根据当前区域是否有更新parentIds来判断是否要更新所有的子区域的parentIds
     */
    @Override
    public int updateArea(SysArea area) {
        int i = 0;
        i += sysAreaMapper.updateByPrimaryKey(area);
        if (area.getParentId() != area.getOldParentId()) {
            i += sysAreaMapper.updateParentIds(area);
        }
        return i;
    }

}
