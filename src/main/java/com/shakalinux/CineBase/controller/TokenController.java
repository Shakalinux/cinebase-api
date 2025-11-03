package com.shakalinux.CineBase.controller;

import com.shakalinux.CineBase.dto.LoginRequestDto;
import com.shakalinux.CineBase.dto.LoginResponseDto;
import com.shakalinux.CineBase.model.Role;
import com.shakalinux.CineBase.model.User;
import com.shakalinux.CineBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = userService.findByEmail(loginRequestDto.email());
        if (user == null || !user.LoginCorrect(loginRequestDto, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Email ou senha inválida");
        }

        var expiresIn = 300L;
        var now = Instant.now();


        var scope = user.getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.joining(" "));


        var claims = JwtClaimsSet.builder()
            .issuer("cinebase")
            .subject(user.getId_users().toString())
            .claim("scope", scope)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiresIn))
            .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDto(jwtValue, expiresIn));
    }



    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers(){
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }







}
