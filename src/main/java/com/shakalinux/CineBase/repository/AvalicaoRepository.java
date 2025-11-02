package com.shakalinux.CineBase.repository;

import com.shakalinux.CineBase.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvalicaoRepository extends JpaRepository<Avaliacao, Long> {
}
