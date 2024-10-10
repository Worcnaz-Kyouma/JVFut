package com.uepg.JVFut.controller;

import com.uepg.JVFut.model.Jogador;
import com.uepg.JVFut.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    private boolean isJogadorInvalido(Jogador jogador) {
        if( jogador.getNome() == null ||
            jogador.getEmail() == null ||
            jogador.getDataNasc() == null) return true;

        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        System.out.println(jogador.getDataNasc());
        LocalDate birthDate = jogador.getDataNasc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return
            jogador.getNome().equals("") ||
            !EMAIL_PATTERN.matcher(jogador.getEmail()).matches() ||
            birthDate.isAfter(LocalDate.now());
    }

    @GetMapping
    public List<Jogador> getAllJogadores() {
        return jogadorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> createJogador(@RequestBody Jogador jogador) {
        try {
            if(isJogadorInvalido(jogador)){
                return new ResponseEntity<>("Dados incorretos ou não permitidos!", HttpStatus.BAD_REQUEST);
            }

            Jogador newJogador = jogadorRepository.save(jogador);

            return ResponseEntity.ok(newJogador);
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro na request ocorreu...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJogadorById(@PathVariable(value = "id") Integer id) {
        try {
            Jogador jogador = jogadorRepository.findById(id).orElse(null);
            if (jogador == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(jogador);
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro na request ocorreu...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJogador(@PathVariable(value = "id") Integer id, @RequestBody Jogador jogadorDetails) {
        try {
            Jogador jogador = jogadorRepository.findById(id).orElse(null);
            if (jogador == null) {
                return ResponseEntity.notFound().build();
            }
            jogador.setNome(jogadorDetails.getNome());
            jogador.setEmail(jogadorDetails.getEmail());
            jogador.setDataNasc(jogadorDetails.getDataNasc());

            if(isJogadorInvalido(jogador)) {
                return new ResponseEntity<>("Dados incorretos ou não permitidos!", HttpStatus.BAD_REQUEST);
            }

            Jogador updatedJogador = jogadorRepository.save(jogador);
            return ResponseEntity.ok(updatedJogador);
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro na request ocorreu...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogador(@PathVariable(value = "id") Integer id) {
        try {
            Jogador jogador = jogadorRepository.findById(id).orElse(null);
            if (jogador == null) {
                return ResponseEntity.notFound().build();
            }

            jogadorRepository.delete(jogador);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Um erro na request ocorreu...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}