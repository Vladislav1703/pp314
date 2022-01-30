package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    User getUser(Long id);
    void deleteUser(Long id);
    List<User> getUsers();
}
