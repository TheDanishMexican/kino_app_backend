package kea.kinoBackend.security.service;

import kea.kinoBackend.project.dto.MovieDTO;
import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.security.dto.UserWithRolesRequest;
import kea.kinoBackend.security.dto.UserWithRolesResponse;
import kea.kinoBackend.security.entity.Role;
import kea.kinoBackend.security.entity.UserWithRoles;
import kea.kinoBackend.security.repository.RoleRepository;
import kea.kinoBackend.security.repository.UserWithRolesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserWithRolesService {

  @Value("${app.default-role:#{null}}")
  private String defaultRoleName;

  private final UserWithRolesRepository userWithRolesRepository;
  private final RoleRepository roleRepository;
  private Role roleToAssign;


  PasswordEncoder passwordEncoder;


  @PostConstruct
  public void init(){
    if(defaultRoleName != null){
      roleToAssign = roleRepository.findById(defaultRoleName).orElse(null);
    }
  }

  //Should be set in application.properties, this is mainly for testing
  public void setDefaultRoleName(String defaultRoleName) {
    this.defaultRoleName = defaultRoleName;
    if (defaultRoleName == null) {
      roleToAssign = null;
    } else {
      roleToAssign = roleRepository.findById(defaultRoleName).orElse(null);
    }
  }


  public UserWithRolesService(UserWithRolesRepository userWithRolesRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userWithRolesRepository = userWithRolesRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserWithRolesResponse getUserWithRoles(String id) {
    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new UserWithRolesResponse(user);
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse addRole(String username, String newRole) {
    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    Role role = roleRepository.findById(newRole).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    user.addRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse removeRole(String username, String roleToRemove) {
    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    Role role = roleRepository.findById(roleToRemove).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
    user.removeRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  //Only way to change roles is via the addRole/removeRole methods
  public UserWithRolesResponse editUserWithRoles(String username, UserWithRolesRequest body) {
    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    user.setEmail(body.getEmail());
    user.setRoles(body.getRoles());
    // Do not change the password
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  /**
   * @param body - the user to be added
   * @return the user added
   */
  public UserWithRolesResponse addUserWithRoles(UserWithRolesRequest body) {
    if (userWithRolesRepository.existsById(body.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user name is taken");
    }
    if (userWithRolesRepository.existsByEmail(body.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is used by another user");
    }
    String pw = body.getPassword();
    UserWithRoles userWithRoles = new UserWithRoles(body.getUsername(), passwordEncoder.encode(pw), body.getEmail());
    setDefaultRole(userWithRoles);
    return new UserWithRolesResponse(userWithRolesRepository.save(userWithRoles));
  }

  private void setDefaultRole(UserWithRoles userWithRoles) {
    if (defaultRoleName != null) {
      if (roleToAssign == null) {
        roleToAssign = roleRepository.findById(defaultRoleName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Default role not found"));
      }
      userWithRoles.addRole(roleToAssign);
    }
  }

    public UserWithRolesResponse deleteUser(String username) {
        UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userWithRolesRepository.delete(user);
        return new UserWithRolesResponse(user);
    }

    public List<UserWithRoles> getAllUsers() {
        return userWithRolesRepository.findAll();
    }

  public UserWithRoles getUser(String username) {
    return userWithRolesRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }
}
