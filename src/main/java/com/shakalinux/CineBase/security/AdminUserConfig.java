package com.shakalinux.CineBase.security;

import com.shakalinux.CineBase.model.Role;
import com.shakalinux.CineBase.model.User;
import com.shakalinux.CineBase.repository.RoleRepository;
import com.shakalinux.CineBase.repository.UserRepostitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepostitory userRepostitory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        if (userRepostitory.findByEmail("admin@cinebase.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@cinebase.com");
            admin.setPassword(bCryptPasswordEncoder.encode("123456"));
            admin.setRoles(Collections.singleton(roleAdmin));
            userRepostitory.save(admin);
            System.out.println("✅ Usuário admin criado com sucesso!");
        } else {
            System.out.println("ℹ️ Usuário admin já existe, não será recriado.");
        }
    }

}
