package com.hpprediction.demo.repository;

import com.hpprediction.demo.entity.Role;
import com.hpprediction.demo.datamodels.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoleEnum name);
    @Query("SELECT r FROM Role r")
    List<Role> getAll();
}
