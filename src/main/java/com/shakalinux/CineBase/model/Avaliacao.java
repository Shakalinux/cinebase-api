package com.shakalinux.CineBase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;

@Entity
@Table(name = "tb_avaliacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;

    @Column(nullable = false, length = 50)
    private String usuario;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private int nota;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id")
    private Filme filme;
}
