package com.auth.authentication.init;

import com.auth.authentication.entity.Role;
import com.auth.authentication.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ApplicationStarterRunner implements ApplicationRunner {

    private final RoleRepo roleRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Creating default admin and user roles");
        // create two roles
        Role roleAdmin = new Role(1l, "123", "ROLE_ADMIN");
        Role roleUser = new Role(2l, "456", "ROLE_USER");

        roleRepo.save(roleAdmin);
        roleRepo.save(roleUser);

    }
}
