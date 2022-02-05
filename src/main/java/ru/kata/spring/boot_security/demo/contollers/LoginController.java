package ru.kata.spring.boot_security.demo.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/create")
    public String createPage(Model map) {
        List<Role> roleSet = roleService.getAllRoles();
        map.addAttribute("roles", roleSet);
        map.addAttribute("userForm", new User());
        return "/registration";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.createUser(user);

        return "redirect:/list";
    }
}
