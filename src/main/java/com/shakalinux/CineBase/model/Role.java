package com.shakalinux.CineBase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.ast.tree.insert.Values;


@Entity
@Table(name = "tb_roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_roles;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    public enum Values{
        BASIC(2L),
        ADMIN(1L);

        long idRoles;

        Values(long idROles){
            this.idRoles = idROles;
        }

    }

}
