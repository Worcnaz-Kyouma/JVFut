package com.uepg.JVFut.controller;

import com.uepg.JVFut.model.Jogador;
import com.uepg.JVFut.model.Pagamento;
import com.uepg.JVFut.repository.JogadorRepository;
import com.uepg.JVFut.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Jogador jogador = jogadorRepository.findById(pagamento.getJogador().getCodJogador())
                .orElseThrow(() -> new RuntimeException("Jogador not found"));

        // Removido para não sobrecarregar o retorno
        // pagamento.setJogador(jogador);

        Pagamento savedPagamento = pagamentoRepository.save(pagamento);

        return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable(value = "id") Integer id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable(value = "id") Integer id, @RequestBody Pagamento pagamentoDetails) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        pagamento.setAno(pagamentoDetails.getAno());
        pagamento.setMes(pagamentoDetails.getMes());
        pagamento.setValor(pagamentoDetails.getValor());
        pagamento.setJogador(pagamentoDetails.getJogador());

        Pagamento updatedPagamento = pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(updatedPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePagamento(@PathVariable(value = "id") Integer id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        pagamentoRepository.delete(pagamento);
        return ResponseEntity.ok().build();
    }
}
