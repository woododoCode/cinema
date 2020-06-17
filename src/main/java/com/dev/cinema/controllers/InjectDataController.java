package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.RoleService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class InjectDataController {
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/inject")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRoles(Set.of(adminRole));
        User user = new User();
        user.setEmail("user@ukr.net");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(Set.of(userRole));
        userService.add(admin);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(admin);
        shoppingCartService.registerNewShoppingCart(user);
        return "Enjoy data from DB!";
    }
}
