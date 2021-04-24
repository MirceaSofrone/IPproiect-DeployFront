package com.hpprediction.demo.userapp.repositories;

import com.hpprediction.demo.userapp.Role;
import com.hpprediction.demo.userapp.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoleEnum name);
}
