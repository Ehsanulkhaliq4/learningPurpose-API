package com.exam.examserver.repo;

import com.exam.examserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);

    public User findByEmailContaining(String email);

    Optional<User> findByEmailToken(String id);

}
