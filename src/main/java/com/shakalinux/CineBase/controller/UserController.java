package com.shakalinux.CineBase.controller;

import com.shakalinux.CineBase.dto.CreateUserDto;
import com.shakalinux.CineBase.model.Role;
import com.shakalinux.CineBase.model.User;
import com.shakalinux.CineBase.service.RoleService;
import com.shakalinux.CineBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto createUserDto) {
        var basicRole = roleService.getRole(Role.Values.BASIC.name());

        if (userService.findUser(createUserDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email já cadastrado");
        }

        User user = new User();
        user.setEmail(createUserDto.email());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        user.setRoles(Set.of(basicRole));
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
