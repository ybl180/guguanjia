import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.SysRole;
import com.dfbz.entity.SysUser;
import com.dfbz.mapper.SysUserMapper;
import com.dfbz.service.SysRoleService;
import com.dfbz.service.SysUserService;
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
public class TestSysUser {
    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void page() {
        Map<String, Object> map = new HashMap<>();
        int[] ints = new int[2];
        ints[0] = 2;
        ints[1] = 1;
        map.put("roleIds", ints);
        List<SysUser> sysUsers = sysUserMapper.selectByCondition(map);
        System.out.println(sysUsers);
    }


}
