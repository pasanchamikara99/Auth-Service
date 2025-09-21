package org.mc.authservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mc.authservice.dto.UserDto;
import org.mc.authservice.models.AppUser;
import org.mc.authservice.models.Role;
import org.mc.authservice.repository.RoleRepository;
import org.mc.authservice.repository.UserRepository;
import org.mc.authservice.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveUser(UserDto request) {

        Role role = roleRepository.findByName(request.getRoleId());

        AppUser user = AppUser.builder()
                .userName(request.getUserName())
                .name(request.getName())
                .password(request.getPassword())
                .role(List.of(role))
                .build();

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(username);

        if (user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
