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
        //Create more users
        UserWithRoles user5 = new UserWithRoles("user5", pwEncoder.encode(passwordUsedByAll), "user5@a.dk");
        UserWithRoles user6 = new UserWithRoles("user6", pwEncoder.encode(passwordUsedByAll), "user6@a.dk");
        UserWithRoles user7 = new UserWithRoles("user7", pwEncoder.encode(passwordUsedByAll), "user7@a.dk");
        UserWithRoles user8 = new UserWithRoles("user8", pwEncoder.encode(passwordUsedByAll), "user8@a.dk");
        UserWithRoles user9 = new UserWithRoles("user9", pwEncoder.encode(passwordUsedByAll), "user9@a.dk");
        UserWithRoles user10 = new UserWithRoles("user10", pwEncoder.encode(passwordUsedByAll), "user10@a.dk");
        UserWithRoles user11 = new UserWithRoles("user11", pwEncoder.encode(passwordUsedByAll), "user11@a.dk");
        UserWithRoles user12 = new UserWithRoles("user12", pwEncoder.encode(passwordUsedByAll), "user12@a.dk");



        user1.addRole(roleUser);
        user1.addRole(roleAdmin);
        user2.addRole(roleUser);
        user3.addRole(roleStaff);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        //Save more users
        userWithRolesRepository.save(user5);
        userWithRolesRepository.save(user6);
        userWithRolesRepository.save(user7);
        userWithRolesRepository.save(user8);
        userWithRolesRepository.save(user9);
        userWithRolesRepository.save(user10);
        userWithRolesRepository.save(user11);
        userWithRolesRepository.save(user12);

    }

}
