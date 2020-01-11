import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.entity.SysRole;
import com.dfbz.mapper.SysRoleMapper;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.management.relation.Role;
import java.util.ArrayList;
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
public class TestSysRole {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleService sysRoleService;

    @Test
    public void page() {
        Map<String, Object> map = new HashMap<>();
        PageInfo<SysRole> pageInfo = sysRoleService.selectPage(map);
        System.out.println(pageInfo);
    }

    @Test
    public void deleteRole() {
        int i = sysRoleService.deleteRole(27L);
    }

    @Test
    public void insertOffice() {
        ArrayList<Long> officeIds = new ArrayList<>();
        officeIds.add(0L);
        officeIds.add(49L);
        int i = sysRoleMapper.insertBatchRoleOffice(1L, officeIds);
    }

    @Test
    public void addRole() {
        SysRole role = new SysRole();
        role.setName("xin角色");
        role.setOfficeId(49L);
        role.setDataScope("9");
        sysRoleMapper.saveRole(role);
    }
}
