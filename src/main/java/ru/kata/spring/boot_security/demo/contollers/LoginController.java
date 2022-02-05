package ru.kata.spring.boot_security.demo.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserServiceImp userService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/list")
    public String getUsersAndRoles (Model map) {
        List<User> userList = userService.allUsers();
        map.addAttribute("users", userList);
        List<Role> roleSet = roleService.getAllRoles();
        map.addAttribute("roles", roleSet);
        return "list";
    }
}
