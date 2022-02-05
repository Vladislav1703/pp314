package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleService roleService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User showUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void createUser(User user) {
        User userSave = userRepository.findByName(user.getUsername());
        if (userSave == null) {
            userRepository.save(user);
        }
    }

    @Override
    public void update(Long id, User updatedUser) {
        User user = userRepository.findByName(updatedUser.getUsername());
        if (user != null) {
            userRepository.save(updatedUser);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleService.getRoleByName(name);
    }

    public User findUserById (Long id) {
        return userRepository.getById(id);
    }
}
