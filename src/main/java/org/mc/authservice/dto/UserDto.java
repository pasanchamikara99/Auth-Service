package org.mc.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    private String name;
    private String userName;
    private String password;
    private String roleId;
}
