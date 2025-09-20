package org.mc.authservice.controller;

import lombok.AllArgsConstructor;
import org.mc.authservice.dto.RoleToUserDto;
import org.mc.authservice.models.AppUser;
import org.mc.authservice.models.Role;
import org.mc.authservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


    @PostMapping("/addRoleToUser")
    public ResponseEntity<List<AppUser>> addUserToRole(@RequestBody RoleToUserDto roleToUserDto) {
        userService.addRoleToUser(roleToUserDto.getUserName(), roleToUserDto.getRoleName());
        return ResponseEntity.ok().build();
    }
}
