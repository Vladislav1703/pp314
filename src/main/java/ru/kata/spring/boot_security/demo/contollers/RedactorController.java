package ru.kata.spring.boot_security.demo.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/redactor")
public class RedactorController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String allUsers(ModelMap map) {
        List<User> userList = userService.getUsers();
        map.addAttribute("users", userList);
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @ModelAttribute("role") Set<Role> roles) {
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/";
    }
}
