package com.uepg.JVFut.controller;

import com.uepg.JVFut.model.Jogador;
import com.uepg.JVFut.model.Pagamento;
import com.uepg.JVFut.repository.JogadorRepository;
import com.uepg.JVFut.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    private boolean isPagamentoInvalido(Pagamento pagamento) {
        if( pagamento.getAno() == null ||
            pagamento.getMes() == null ||
            pagamento.getValor() == null ||
            pagamento.getJogador() == null) return true;

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        if (pagamento.getAno() > currentYear && pagamento.getAno() < 0) {
            return true;
        }

        if (pagamento.getMes() < 1 || pagamento.getMes() > 12 ||
                (pagamento.getAno() == currentYear && pagamento.getMes() > currentMonth)) {
            return true;
        }

        if (pagamento.getValor() < 0) {
            return true;
        }

        return false;
    }

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> createPagamento(@RequestBody Pagamento pagamento) {
        Optional<Jogador> jogador = jogadorRepository.findById(pagamento.getJogador().getCodJogador());
        if(jogador.isEmpty()) return new ResponseEntity<>("Bad Request: Jogador deste codJogador não existe!", HttpStatus.BAD_REQUEST);
        // Removido para não sobrecarregar o retorno
        // pagamento.setJogador(jogador);

        if(isPagamentoInvalido(pagamento)) {
            return new ResponseEntity<>("Bad Request: Dados incorretos ou não permitidos!", HttpStatus.BAD_REQUEST);
        }

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
    public ResponseEntity<Object> updatePagamento(@PathVariable(value = "id") Integer id, @RequestBody Pagamento pagamentoDetails) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        pagamento.setAno(pagamentoDetails.getAno());
        pagamento.setMes(pagamentoDetails.getMes());
        pagamento.setValor(pagamentoDetails.getValor());
        pagamento.setJogador(pagamentoDetails.getJogador());

        if(isPagamentoInvalido(pagamento)) {
            return new ResponseEntity<>("Bad Request: Dados incorretos ou não permitidos!", HttpStatus.BAD_REQUEST);
        }

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
