
import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.Statute;
import com.dfbz.mapper.StatuteMapper;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestStatute {

    @Autowired
    StatuteMapper mapper;

    @Autowired
    StatuteService service;

    @Test
    public void test2() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 5);
        params.put("type", 1);
        PageInfo<Statute> statutePageInfo = service.selectByCondition(params);

        PageInfo<Statute> statutePageInfo1 = service.selectByCondition(params);

    }


}
