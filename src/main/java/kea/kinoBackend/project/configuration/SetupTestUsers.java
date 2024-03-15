package kea.kinoBackend.project.configuration;

import kea.kinoBackend.security.entity.Role;
import kea.kinoBackend.security.entity.UserWithRoles;
import kea.kinoBackend.security.repository.RoleRepository;
import kea.kinoBackend.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class SetupTestUsers implements ApplicationRunner {
    UserWithRolesRepository userWithRolesRepository;
    RoleRepository roleRepository;

    PasswordEncoder pwEncoder;

    String passwordUsedByAll;

    public SetupTestUsers(UserWithRolesRepository userWithRolesRepository, RoleRepository roleRepository, PasswordEncoder pwEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.roleRepository = roleRepository;
        this.pwEncoder = pwEncoder;
        this.passwordUsedByAll = "test12";
    }

    public void run(ApplicationArguments args) {
        createTestUsers();
    }

    private void createTestUsers() {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("STAFF"));
        roleRepository.save(new Role("ADMIN"));

        Role roleUser = roleRepository.findById("USER").orElseThrow(()-> new NoSuchElementException("Role 'user' not found"));
        Role roleAdmin = roleRepository.findById("ADMIN").orElseThrow(()-> new NoSuchElementException("Role 'admin' not found"));
        Role roleStaff = roleRepository.findById("STAFF").orElseThrow(()-> new NoSuchElementException("Role 'staff' not found"));

        UserWithRoles user1 = new UserWithRoles("user1", pwEncoder.encode(passwordUsedByAll), "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", pwEncoder.encode(passwordUsedByAll), "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", pwEncoder.encode(passwordUsedByAll), "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", pwEncoder.encode(passwordUsedByAll), "user4@a.dk");
        user1.addRole(roleUser);
        user1.addRole(roleAdmin);
        user2.addRole(roleUser);
        user3.addRole(roleStaff);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }

}
