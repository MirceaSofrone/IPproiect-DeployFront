package com.fii.houses.fii.houses.demo.repository;

import com.fii.houses.fii.houses.demo.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {
}
