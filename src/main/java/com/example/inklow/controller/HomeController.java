package com.example.inklow.controller;

import com.example.inklow.entities.Role;
import com.example.inklow.entities.RolePermission;
import com.example.inklow.entities.User;
import com.example.inklow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {
    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final UserRoleService userRoleService;
    private final RolePermissionService rolePermissionService;

    @Autowired
    public HomeController(UserService userService,
                          RoleService roleService,
                          PermissionService permissionService,
                          UserRoleService userRoleService,
                          RolePermissionService rolePermissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getListOfUsers());
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User tempUser = userService.handleAccountRegistration(user);

        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempUser);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        User tempUser = userService.handleAccountDeletion(user);

        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempUser);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteAllUser", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAllUser() {
        Boolean deleteAll = userService.handleAllAccountDeletion();

        if (deleteAll == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(deleteAll);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "getAllRoles", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getListOfRoles());
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        Role tempRole = roleService.handleRoleRegistration(role);

        if (tempRole == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRole);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRole(@RequestBody Role role) {
        Role tempRole = roleService.handleRoleDeletion(role);

        if (tempRole == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRole);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteAllRole", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPermissions() {
        Boolean deleteAll = roleService.handleAllRoleDeletion();

        if (deleteAll == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.getListOfPermission());
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "getAllRolePermissions", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRolePermissions() {
        return ResponseEntity.status(HttpStatus.OK).body(rolePermissionService.getListOfUserRoles());
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "addRolePermission", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRole(@RequestBody RolePermission rolePermission) {
        RolePermission tempRolePermission = rolePermissionService.handleRolePermissionRegistration(rolePermission);

        if (tempRolePermission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRolePermission);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteRolePermission", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRolePermission(@RequestBody RolePermission rolePermission) {
        RolePermission tempRolePermission = rolePermissionService.handleRolePermissionRegistration(rolePermission);

        if (tempRolePermission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRolePermission);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteAllRolePermission", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAllRolePermission() {
        Boolean deleteAll = rolePermissionService.handleAllRolePermissionDeletion();

        if (deleteAll == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(deleteAll);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "getAllUserRoles", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(userRoleService.getListOfUserRoles());
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "addUserRole", method = RequestMethod.POST)
    public ResponseEntity<?> addUserRole(@RequestBody RolePermission rolePermission) {
        RolePermission tempRolePermission = rolePermissionService.handleRolePermissionRegistration(rolePermission);

        if (tempRolePermission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRolePermission);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteUserRole", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUserRole(@RequestBody RolePermission rolePermission) {
        RolePermission tempRolePermission = rolePermissionService.handleRolePermissionDeletion(rolePermission);

        if (tempRolePermission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRolePermission);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "deleteAllUserRole", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAllUserRole() {
        Boolean tempRolePermission = rolePermissionService.handleAllRolePermissionDeletion();

        if (tempRolePermission == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tempRolePermission);
    }
}
