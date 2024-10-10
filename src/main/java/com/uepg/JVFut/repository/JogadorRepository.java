package com.uepg.JVFut.repository;

import com.uepg.JVFut.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}