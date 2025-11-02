package com.shakalinux.CineBase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID idUsers;

    @Email(message= "Email invalido")
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(70)")
    private String email;

    @Size(min = 3, max = 10, message = "A senha deve esta contida entre 3 a 10 caracteres")
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();




}
