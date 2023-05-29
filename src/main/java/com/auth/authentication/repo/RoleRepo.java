package com.auth.authentication.repo;

import com.auth.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    // find role by rolename
    Role findRoleByName(String name);
}
