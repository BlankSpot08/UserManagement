package com.example.inklow.controller;

import com.example.inklow.entities.User;
import com.example.inklow.security.util.JwtUtil;
import com.example.inklow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/api" + UserController.USER_ENDPOINTS.USER)
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(final UserService userService, final JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize(value = "hasAuthority('CAN_VIEW_ALL_USER_PROFILE')")
    @RequestMapping(value = USER_ENDPOINTS.GET_ALL, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
        List<User> listOfUsers = userService.getListOfUsers();

        return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
    }

    @PreAuthorize(value = "hasAnyAuthority('CAN_VIEW_USER_PROFILE')")
    @RequestMapping(value = USER_ENDPOINTS.GET, method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.split(" ")[1];

        String username = jwtUtil.extractUsername(jwt);

        User user = userService.findUserByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PreAuthorize(value = "hasAnyAuthority('CAN_UPDATE_USER_PROFILE')")
    @RequestMapping(value = USER_ENDPOINTS.UPDATE, method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User tempUser = userService.findUserByUsername(user.getUsername());

        userService.handleAccountChanges(tempUser);

        return ResponseEntity.status(HttpStatus.OK).body(tempUser);
    }

    @PreAuthorize(value = "hasAnyAuthority('CAN_DELETE_USER_PROFILE')")
    @RequestMapping(value = USER_ENDPOINTS.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        User tempUser = userService.findUserByUsername(user.getUsername());

        userService.handleAccountDeletion(tempUser);

        return ResponseEntity.status(HttpStatus.OK).body(tempUser);
    }

    protected static final class USER_ENDPOINTS {
        protected static final String USER = "/user";

        protected static final String GET = "/get";

        protected static final String GET_ALL = "/getAll";

        protected static final String UPDATE = "/update";

        protected static final String DELETE = "/delete";
    }
}
