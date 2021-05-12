package com.fii.houses.fii.houses.demo.repository;

import com.fii.houses.fii.houses.demo.models.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserActivityRepository extends JpaRepository<UserActivity, UUID> {
}
