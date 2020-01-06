import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.Qualification;
import com.dfbz.entity.SysOffice;
import com.dfbz.entity.WorkOrder;
import com.dfbz.mapper.*;
import com.dfbz.service.QualificationService;
import com.dfbz.service.SysOfficeService;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/25 16:16
 * @desciption
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestSysOffice {
    @Autowired
    SysOfficeService sysOfficeService;

    @Test
    public void testOffice() {
        List<SysOffice> sysOffices = sysOfficeService.selectAll();
        System.out.println("--------------------------------------------------");
        List<SysOffice> sysOffices1 = sysOfficeService.selectAll();
    }

    @Test
    public void testUpdateOffice() {
        SysOffice sysOffice = sysOfficeService.selectByPrimaryKey(2);
        sysOffice.setPhone("111111");
        System.out.println("-----------------------------------------------------");
        int i = sysOfficeService.updateByPrimaryKeySelective(sysOffice);
    }

    @Autowired
    SysOfficeMapper mapper;
    @Test
    public void tests(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","重");
        List<SysOffice> sysOffices = mapper.selectByCondition(map);
    }

}
