package com.example.inklow.daoImpTest;

import com.example.inklow.dao.RoleDao;
import com.example.inklow.dao.RolePermissionsDao;
import com.example.inklow.entities.Permission;
import com.example.inklow.entities.Role;
import com.example.inklow.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoleDaoImpTest implements RoleDao {
    private final JdbcTemplate jdbcTemplate;
    private final RolePermissionsDao rolePermissionsDao;

    @Autowired
    public RoleDaoImpTest(final JdbcTemplate jdbcTemplate, RolePermissionsDao rolePermissionsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.rolePermissionsDao = rolePermissionsDao;
    }

    @Override
    public List<Role> getListOfRole() {
        String query = "SELECT * FROM roles";

        List<Role> roles = jdbcTemplate.query(query, new RoleMapper());

        for (Role role : roles) {
            List<Permission> permissions = rolePermissionsDao.getRolePermissionsById(role.getId());
            role.setPermissions(permissions);
        }

        return roles;
    }

    @Override
    public Role getRoleById(UUID id) {
        String query = "SELECT * FROM roles WHERE id = ?";

        Role role = jdbcTemplate.queryForObject(query, new Object[]{id}, new RoleMapper());

        List<Permission> permissions = rolePermissionsDao.getRolePermissionsById(role.getId());
        role.setPermissions(permissions);

        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        String query = "SELECT * FROM roles WHERE name = ?";

        Role role = jdbcTemplate.queryForObject(query, new Object[]{name}, new RoleMapper());

        List<Permission> permissions = rolePermissionsDao.getRolePermissionsByName(role.getName());
        role.setPermissions(permissions);

        return role;
    }

    @Override
    public Role addRole(Role role) {
        String query = "INSERT INTO roles" +
                "(name, description) " +
                "VALUES (?, ?)";

        jdbcTemplate.update(query, role.getName(), role.getDescription());

        return role;
    }

    @Override
    public Role removeRole(Role role) {
        String query = "DELETE FROM roles WHERE id = ?";

        jdbcTemplate.update(query, role.getId());

        return role;
    }
}