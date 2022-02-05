package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User showUser(Long id);

    void createUser(User user);

    void update(Long id, User updatedUser);

    void delete(Long id);

    User findByUsername(String username);

    Role getRoleByName(String name);
}
