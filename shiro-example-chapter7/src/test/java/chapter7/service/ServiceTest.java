package chapter7.service;

import chapter7.BaseTest;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class ServiceTest extends BaseTest {


    @Test
    public void testUserRolePermissionRelation() {

        //zhang，一个角色，两个权限
        Set<String> roles = userService.findRoles(u1.getUsername());
        Assert.assertEquals(1, roles.size());
        Assert.assertTrue(roles.contains(r1.getRole()));

        Set<String> permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(2, permissions.size());
        Assert.assertTrue(permissions.contains(p2.getPermission()));

        //wang
        roles = userService.findRoles(u2.getUsername());
        Assert.assertEquals(0, roles.size());
        permissions = userService.findPermissions(u2.getUsername());
        Assert.assertEquals(0, permissions.size());


        //解除 admin-menu:update关联
        roleService.uncorrelationPermissions(r1.getId(), p2.getId());
        permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(1, permissions.size());
        Assert.assertFalse(permissions.contains(p2.getPermission()));


        //删除一个permission
        permissionService.deletePermission(p2.getId());
        permissions = userService.findPermissions(u1.getUsername());
        Assert.assertEquals(1, permissions.size());

        //解除 zhang-admin关联
        userService.uncorrelationRoles(u1.getId(), r1.getId());
        roles = userService.findRoles(u1.getUsername());
        Assert.assertEquals(0, roles.size());


    }

}
