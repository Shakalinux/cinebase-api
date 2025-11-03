package com.shakalinux.CineBase.model;

import com.shakalinux.CineBase.dto.LoginRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_users;

    @Email(message= "Email invalido")
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(70)")
    private String email;

    @Size(min = 3, message = "A senha deve ter pelo menos 3 caracteres")
    @Column(nullable = false, length = 255)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public boolean LoginCorrect(LoginRequestDto loginRequestDto, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginRequestDto.password(), this.password);
    }


}
