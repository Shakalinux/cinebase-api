package com.shakalinux.CineBase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_generos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_generos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private Values nome;

    public enum Values {
        ACAO,
        AVENTURA,
        COMEDIA,
        DRAMA,
        FICCAO,
        ROMANCE,
        TERROR,
        SUSPENSE,
        ANIMACAO
    }
}
