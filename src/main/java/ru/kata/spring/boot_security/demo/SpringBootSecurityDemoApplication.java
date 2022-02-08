package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	@Autowired
	private UserServiceImp userServiceImp;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			Role admin = new Role("ROLE_ADMIN");
			Role user = new Role("ROLE_USER");
			roleService.createRole(admin);
			roleService.createRole(user);

			User userAdmin = new User();
			userAdmin.setPassword("admin");
			userAdmin.setEmail("admin");
			userAdmin.setAge(27);
			userAdmin.setName("kertka");
			userAdmin.setLastName("bein");
			Set<Role> roleSetAdmin = new HashSet<>();
			roleSetAdmin.add(admin);
			userAdmin.setRoles(roleSetAdmin);

			User userUser = new User();
			userUser.setPassword("123q123q");
			userUser.setEmail("user");
			userUser.setAge(14);
			userUser.setName("name");
			userUser.setLastName("lastname");
			Set<Role> roleSetUser = new HashSet<>();
			roleSetUser.add(user);
			userUser.setRoles(roleSetUser);

			userServiceImp.createUser(userAdmin);
			userServiceImp.createUser(userUser);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

}


