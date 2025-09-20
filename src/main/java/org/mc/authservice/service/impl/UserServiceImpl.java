package org.mc.authservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mc.authservice.models.AppUser;
import org.mc.authservice.models.Role;
import org.mc.authservice.repository.RoleRepository;
import org.mc.authservice.repository.UserRepository;
import org.mc.authservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRole().add(role);
    }

    @Override
    public AppUser getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
