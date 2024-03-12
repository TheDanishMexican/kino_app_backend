package kea.kinoBackend.security.dto;


import kea.kinoBackend.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWithRolesResponse {
    String userName;
    List<String> roleNames;
    String email;

    public UserWithRolesResponse(UserWithRoles userWithRoles){
        this.userName = userWithRoles.getUsername();
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.getRoleName()).toList();
        this.email = userWithRoles.getEmail();
    }

}
