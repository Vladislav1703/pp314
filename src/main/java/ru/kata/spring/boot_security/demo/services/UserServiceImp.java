package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
    @PersistenceContext
    private EntityManager em;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImp () {

    }

    public UserServiceImp(@Qualifier("UserRepository") UserRepository userRepository,@Qualifier("RoleRepository") RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        User userFromDB = userRepository.findByEmail(user.getUsername());
        if (userFromDB == null) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(User user) {
        User userFromDB = userRepository.findByEmail(user.getUsername());
        if (userFromDB != null) {
            userRepository.save(user);
        }
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
