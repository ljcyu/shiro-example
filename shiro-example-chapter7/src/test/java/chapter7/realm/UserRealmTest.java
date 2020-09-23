package chapter7.realm;

import chapter7.BaseTest;
import junit.framework.Assert;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class UserRealmTest extends BaseTest {

    public static final String CONFIG_FILE = "classpath:shiro-jdbc.ini";

    @Test
      public void testLoginSuccess() {
        login(CONFIG_FILE, u1.getUsername(), password);
        Assert.assertTrue(subject().isAuthenticated());
    }

    @Test(expected = UnknownAccountException.class)
    public void testLoginFailWithUnknownUsername() {
        login(CONFIG_FILE, u1.getUsername() + "1", password);
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void testLoginFailWithErrorPassowrd() {
        login(CONFIG_FILE, u1.getUsername(), password + "1");
    }


    @Test(expected = ExcessiveAttemptsException.class)
    public void testLoginFailWithLimitRetryCount() {
        for(int i = 1; i <= 5; i++) {
            try {
                login(CONFIG_FILE, u2.getUsername(), password + "1");
            } catch (Exception e) {/*ignore*/}
        }
        login(CONFIG_FILE, u2.getUsername(), password + "1");

        //需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
    }

    @Test
    public void testHasRole() {
        login(CONFIG_FILE, u1.getUsername(), password );
        Assert.assertTrue(subject().hasRole("admin"));
    }

    @Test
    public void testNoRole() {
        login(CONFIG_FILE, u2.getUsername(), password);
        Assert.assertFalse(subject().hasRole("admin"));
    }

    @Test
    public void testHasPermission() {
        login(CONFIG_FILE, u1.getUsername(), password);
        Assert.assertTrue(subject().isPermittedAll("user:create", "menu:create"));
    }

    @Test
    public void testNoPermission() {
        login(CONFIG_FILE, u2.getUsername(), password);
        Assert.assertFalse(subject().isPermitted("user:create"));
    }
}
