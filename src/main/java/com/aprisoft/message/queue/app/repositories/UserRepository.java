package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository1")
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
