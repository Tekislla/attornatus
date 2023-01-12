package com.attornatus.controller;

import com.attornatus.dto.EnderecoCadastroDTO;
import com.attornatus.entity.Endereco;
import com.attornatus.entity.Pessoa;
import com.attornatus.service.EnderecoService;
import com.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @Autowired
    PessoaService pessoaService;

    @PostMapping(value = "/{pessoaId}")
    public ResponseEntity<Endereco> adicionaEndereco(@PathVariable("pessoaId") Long pessoaId, @RequestBody EnderecoCadastroDTO dto) {
       Pessoa p = pessoaService.findById(pessoaId);

        if (p != null) {
            try {
                return ResponseEntity.ok(enderecoService.adicionaEndereco(p, dto));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping(value = "/{pessoaId}")
    public ResponseEntity<List<Endereco>> listEnderecosByPessoa(@PathVariable("pessoaId") Long pessoaId) {
        Pessoa p = pessoaService.findById(pessoaId);

        if (p != null) {
            List<Endereco> enderecos = enderecoService.listEnderecosByPessoa(pessoaId);

            if (!enderecos.isEmpty()) {
                return ResponseEntity.ok(enderecos);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/principal/{pessoaId}")
    public ResponseEntity<Endereco> getEnderecoPrincipal(@PathVariable("pessoaId") Long pessoaId) {
        Pessoa p = pessoaService.findById(pessoaId);

        if (p != null) {
            Endereco endereco = enderecoService.findEnderecoPrincipal(pessoaId);

            if (endereco != null) {
                return ResponseEntity.ok(endereco);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
