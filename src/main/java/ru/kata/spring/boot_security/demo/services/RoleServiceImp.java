package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    @PersistenceContext
    private RoleRepository roleRepository;

    public RoleServiceImp() {
    }

    @Autowired
    public RoleServiceImp(@Qualifier("RoleRepository") RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
