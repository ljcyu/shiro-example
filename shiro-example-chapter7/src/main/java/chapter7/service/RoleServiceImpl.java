package chapter7.service;

import chapter7.dao.RoleDao;
import chapter7.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDaoImpl;


    public Role createRole(Role role) {
        return roleDaoImpl.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDaoImpl.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDaoImpl.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDaoImpl.uncorrelationPermissions(roleId, permissionIds);
    }

}
