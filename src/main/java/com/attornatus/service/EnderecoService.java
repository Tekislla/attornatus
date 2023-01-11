package com.attornatus.service;

import com.attornatus.dto.EnderecoCadastroDTO;
import com.attornatus.entity.Endereco;
import com.attornatus.entity.Pessoa;
import com.attornatus.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repo;

    public Endereco criaEndereco(EnderecoCadastroDTO dto, Long pessoaId) {
        Endereco e = new Endereco();

        e.setPessoaId(pessoaId);
        e.setLogradouro(dto.getLogradouro());
        e.setCep(dto.getCep());
        e.setNumero(dto.getNumero());
        e.setCidade(dto.getCidade());
        e.setEnderecoPrincipal(true);

        repo.save(e);
        return e;
    }

    public void adicionaEndereco(Pessoa p, Endereco e) {
        if (p.getEnderecos().isEmpty()) {
            p.setEnderecos(Arrays.asList(e));
        } else {
            p.getEnderecos().add(e);
        }
    }
}
