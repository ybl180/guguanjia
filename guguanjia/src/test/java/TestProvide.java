import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.Qualification;
import com.dfbz.entity.WorkOrder;
import com.dfbz.mapper.*;
import com.dfbz.service.QualificationService;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
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
public class TestProvide {
    @Autowired
    QualificationMapper mapper;
    @Autowired
    QualificationService service;

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
//        List<Qualification> list = mapper.selectByCondition(map);
        PageInfo<Qualification> page = service.selectByCondition(map);
        System.out.println(page);
    }


    @Autowired
    ExamineMapper examineMapper;

    @Test
    public void TestExamine() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
        examineMapper.selectByCondition(map);
    }


    @Autowired
    WorkOrderService workOrderService;
    @Autowired
    WorkOrderMapper workOrderMapper;

    @Test
    public void testWork() {
        Map<String, Object> map = new HashMap<>();
//        map.put("status", 2);
//        map.put("end", new Date());
//        map.put("officeId", 49);
//        List<WorkOrder> list = workOrderMapper.selectByCondition(map);
        PageInfo<WorkOrder> list2 = workOrderService.selectPage(map);
        System.out.println(list2);
//        System.out.println(list);
    }


    @Autowired
    SysUserMapper userMapper;

    @Test
    public void testUser() {
        userMapper.selectById(15L);
    }


    @Autowired
    DetailMapper detailMapper;

    @Test
    public void testDetail() {
        detailMapper.selectByOid(15L);
    }

    @Autowired
    TransferMapper transferMapper;
    @Test
    public void testTransfer(){
        transferMapper.selectByOid(15L);
    }

}
