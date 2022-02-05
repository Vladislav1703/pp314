package ru.kata.spring.boot_security.demo.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServiceImp userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    public String allUsers(ModelMap map) {
        List<User> userList = userService.allUsers();
        map.addAttribute("users", userList);
        List<Role> roleSet = roleService.getAllRoles();
        map.addAttribute("roles", roleSet);
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.findUserById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.update(user.getId(), user);
        return "redirect:/admin/users";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role") String[] role) {
        System.out.println(role);
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.createUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/create")
    public String createPage(Model map) {
        List<Role> roleSet = roleService.getAllRoles();
        map.addAttribute("roles", roleSet);
        map.addAttribute("userForm", new User());
        return "/registration";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        System.out.println("id" + id);
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
