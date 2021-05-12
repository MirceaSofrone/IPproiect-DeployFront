package com.fii.houses.fii.houses.demo.repository;

import com.fii.houses.fii.houses.demo.models.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, UUID> {
}
