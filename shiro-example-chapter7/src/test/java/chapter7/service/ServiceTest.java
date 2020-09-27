package chapter7.service;

import chapter7.BaseTest;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class ServiceTest extends BaseTest {


    @Test
    public void testUserRolePermissionRelation() {

        //zhang，一个角色，两个权限
        Set<String> roles = userServiceImpl.findRoles(u1.getUsername());
        assertEquals(1, roles.size());
        assertTrue(roles.contains(r1.getRole()));

        Set<String> permissions = userServiceImpl.findPermissions(u1.getUsername());
        assertEquals(2, permissions.size());
        Assert.assertTrue(permissions.contains(p2.getPermission()));

        //wang
        roles = userServiceImpl.findRoles(u2.getUsername());
        Assert.assertEquals(0, roles.size());
        permissions = userServiceImpl.findPermissions(u2.getUsername());
        Assert.assertEquals(0, permissions.size());


        //解除 admin-menu:update关联
        roleServiceImpl.uncorrelationPermissions(r1.getId(), p2.getId());
        permissions = userServiceImpl.findPermissions(u1.getUsername());
        Assert.assertEquals(1, permissions.size());
        Assert.assertFalse(permissions.contains(p2.getPermission()));


        //删除一个permission
        permissionServiceImpl.deletePermission(p2.getId());
        permissions = userServiceImpl.findPermissions(u1.getUsername());
        Assert.assertEquals(1, permissions.size());

        //解除 zhang-admin关联
        userServiceImpl.uncorrelationRoles(u1.getId(), r1.getId());
        roles = userServiceImpl.findRoles(u1.getUsername());
        Assert.assertEquals(0, roles.size());


    }

}
