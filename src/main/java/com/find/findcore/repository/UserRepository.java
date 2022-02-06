package com.find.findcore.repository;

import com.find.findcore.model.enumeration.Provider;
import com.find.findcore.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAndProvider(String email, Provider provider);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndProvider(String email, Provider provider);

    Optional<User> findByResetToken(String Token);

    boolean existsByEmail(String email);
}