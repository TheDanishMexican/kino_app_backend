package kea.kinoBackend.security.dto;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRolesRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;
}