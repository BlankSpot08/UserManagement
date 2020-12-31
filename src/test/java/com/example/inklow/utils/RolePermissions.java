package com.example.inklow.utils;

import com.example.inklow.dao.RoleDao;
import com.example.inklow.dao.RolePermissionsDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RolePermissions {
    private final RolePermissionsDao rolePermissionsDao;

    @Autowired
    public RolePermissions(RolePermissionsDao rolePermissionsDao) {
        this.rolePermissionsDao = rolePermissionsDao;
    }

    @Test
    void getRolePermissions() {
        rolePermissionsDao.getRolePermissions().forEach(e -> {
            System.out.println("Role Id: " + e.getRoleId());
            System.out.println("Permission Id: " + e.getPermissionId());
            System.out.println();
        });
    }
}
