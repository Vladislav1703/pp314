package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

@Qualifier("UserRepository")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
