package com.uepg.JVFut.controller;

import com.uepg.JVFut.model.Jogador;
import com.uepg.JVFut.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping
    public List<Jogador> getAllJogadores() {
        return jogadorRepository.findAll();
    }

    @PostMapping
    public Jogador createJogador(@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable(value = "id") Integer id) {
        Jogador jogador = jogadorRepository.findById(id).orElse(null);
        if (jogador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(jogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable(value = "id") Integer id, @RequestBody Jogador jogadorDetails) {
        Jogador jogador = jogadorRepository.findById(id).orElse(null);
        if (jogador == null) {
            return ResponseEntity.notFound().build();
        }

        jogador.setNome(jogadorDetails.getNome());
        jogador.setEmail(jogadorDetails.getEmail());
        jogador.setDatanasc(jogadorDetails.getDataNasc());

        Jogador updatedJogador = jogadorRepository.save(jogador);
        return ResponseEntity.ok(updatedJogador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogador(@PathVariable(value = "id") Integer id) {
        Jogador jogador = jogadorRepository.findById(id).orElse(null);
        if (jogador == null) {
            return ResponseEntity.notFound().build();
        }

        jogadorRepository.delete(jogador);
        return ResponseEntity.ok().build();
    }
}