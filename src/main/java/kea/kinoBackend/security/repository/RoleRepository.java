package kea.kinoBackend.security.repository;

import kea.kinoBackend.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
