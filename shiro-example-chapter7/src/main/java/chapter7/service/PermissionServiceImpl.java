package chapter7.service;

import chapter7.dao.PermissionDao;
import chapter7.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDaoImpl;

    public Permission createPermission(Permission permission) {
        return permissionDaoImpl.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDaoImpl.deletePermission(permissionId);
    }
}
