package org.mc.authservice.service;

import org.mc.authservice.dto.UserDto;
import org.mc.authservice.models.AppUser;
import org.mc.authservice.models.Role;

import java.util.List;

public interface UserService {

    AppUser saveUser(UserDto user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    AppUser getUserByName(String userName);

    List<AppUser> getAllUsers();

}
