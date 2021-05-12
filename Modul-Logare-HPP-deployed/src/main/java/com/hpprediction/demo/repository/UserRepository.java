package com.hpprediction.demo.repository;



import com.hpprediction.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.isEnabled = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.password = ?2 WHERE a.email = ?1")
    void modifyPassword(String email, String password);

}
