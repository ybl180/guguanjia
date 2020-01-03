import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysAreaListener;
import com.dfbz.mapper.SysAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/3 10:36
 * @desciption
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestExcel {
    @Autowired
    SysAreaMapper sysAreaMapper;

    @Test
    public void testWrite() {
        String fileName = "E://test.xlsx";
        //获excel写出对象
        ExcelWriter excelWriter = EasyExcel.write(fileName, SysArea.class).build();
        //获取sheet对象
        WriteSheet writeSheet = EasyExcel.writerSheet("sysArea数据").build();
        List<SysArea> sysAreas = sysAreaMapper.selectAll();

        excelWriter.write(sysAreas, writeSheet);
        excelWriter.finish();

    }


    /**
     * 读取excel信息，变成java对象
     * 1.编写一个实现了监听器接口的类
     * 2.获取excel读取对象
     * 3.获取sheet对象
     * 4.读资源
     */
    @Test
    public void testRead() {
        String fileName = "E://test.xlsx";
        ExcelReader reader = EasyExcel.read(fileName, SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);//读资源
        reader.finish();
    }
}
