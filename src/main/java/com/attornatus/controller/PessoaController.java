package com.attornatus.controller;

import com.attornatus.dto.PessoaCadastroDTO;
import com.attornatus.entity.Pessoa;
import com.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    PessoaService service;

    @PostMapping(value = "/criar")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaCadastroDTO dto) {
        try {
            return ResponseEntity.ok(service.criaPessoa(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Pessoa> cadastraPessoaComEndereço(@RequestBody PessoaCadastroDTO dto) {
        try {
            return ResponseEntity.ok(service.cadastraPessoaComEndereco(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/editar/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathParam("id") Long id, @RequestBody PessoaCadastroDTO dto) {
        Pessoa p = service.findById(id);

        if (p != null) {
            try {
                return ResponseEntity.ok(service.editarPessoa(id, dto));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathParam("id") Long id) {
        Pessoa p = service.findById(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
