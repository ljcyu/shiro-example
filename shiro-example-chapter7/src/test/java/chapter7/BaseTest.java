package chapter7;

import chapter7.service.*;
import chapter7.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@SpringBootTest
public abstract class BaseTest {
    @Autowired
    protected PermissionService permissionServiceImpl;
    @Autowired
    protected RoleService roleServiceImpl;
    @Autowired
    protected UserService userServiceImpl;
    @Autowired
    private JdbcTemplate JdbcTemplate;

    protected String password = "123";

    protected Permission p1;
    protected Permission p2;

    protected Role r1;

    protected User u1;
    protected User u2;

    @Before
    public void setUp() {
        JdbcTemplate.update("delete from sys_users");
        JdbcTemplate.update("delete from sys_roles");
        JdbcTemplate.update("delete from sys_permissions");
        JdbcTemplate.update("delete from sys_users_roles");
        JdbcTemplate.update("delete from sys_roles_permissions");

        //1、新增权限
        p1 = new Permission("user:*", "用户模块", Boolean.TRUE);
        p2 = new Permission("menu:*", "菜单模块", Boolean.TRUE);
        permissionServiceImpl.createPermission(p1);
        permissionServiceImpl.createPermission(p2);

        //2、新增角色
        r1 = new Role("admin", "管理员", Boolean.TRUE);

        roleServiceImpl.createRole(r1);

        //3、关联角色-权限
        roleServiceImpl.correlationPermissions(r1.getId(), p1.getId());
        roleServiceImpl.correlationPermissions(r1.getId(), p2.getId());

        //4、新增用户
        u1 = new User("zhang", password);
        u2 = new User("wang", password);

        userServiceImpl.createUser(u1);
        userServiceImpl.createUser(u2);

        //5、关联用户-角色
        userServiceImpl.correlationRoles(u1.getId(), r1.getId());
    }




    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    protected void login(String configFile, String username, String password) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

}
