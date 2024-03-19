package kea.kinoBackend.security.api;

import kea.kinoBackend.project.dto.MovieDTO;
import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.security.dto.UserWithRolesRequest;
import kea.kinoBackend.security.dto.UserWithRolesResponse;
import kea.kinoBackend.security.entity.UserWithRoles;
import kea.kinoBackend.security.service.UserWithRolesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  UserWithRolesService userWithRolesService;

  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }

  //Anonymous users can call this.
  @PostMapping
  @Operation(summary = "Add a new UserWithRoles user",
          description = "If a default role is defined (app.default-role ), this role will be assigned to the user.")
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles(request);
  }

  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/add-role/{username}/{role}")
  @Operation(summary = "Add a role to a UserWithRoles", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.addRole(username, role);
  }

  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/remove-role/{username}/{role}")
  @Operation(summary = "Removes a role from a UserWithRoles", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.removeRole(username, role);
  }

  //Delete a user from the system.
  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{username}")
  @Operation(summary = "Delete a user", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse deleteUser(@PathVariable String username) {
    try {
      return userWithRolesService.deleteUser(username);
    } catch (Exception e) {
      throw new RuntimeException("User not found");
    }

  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/users")
  @Operation(summary = "Get all users", description = "Caller must be authenticated with the role ADMIN")
  public List<UserWithRoles> getAllUsers() {
    return userWithRolesService.getAllUsers();
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/update-user/{username}")
  @Operation(summary = "Update a user", description = "Caller must be authenticated with the role ADMIN")
  public UserWithRolesResponse editUserWithRoles(@PathVariable String username, @RequestBody UserWithRolesRequest body) {
    UserWithRoles updatedUser = userWithRolesService.getUser(username);

    updatedUser.setEmail(body.getEmail());
    updatedUser.setRoles(body.getRoles());
    updatedUser.setEdited(LocalDateTime.now());

    return userWithRolesService.editUserWithRoles(username, new UserWithRolesRequest(updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getEmail(), updatedUser.getRoles()));
  }
}

