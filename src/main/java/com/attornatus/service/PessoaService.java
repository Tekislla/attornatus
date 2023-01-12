package com.attornatus.service;

import com.attornatus.dto.PessoaCadastroDTO;
import com.attornatus.entity.Endereco;
import com.attornatus.entity.Pessoa;
import com.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository repo;

    @Autowired
    EnderecoService enderecoService;

    public Pessoa criaPessoa(PessoaCadastroDTO dto) throws ParseException {
        Pessoa p = new Pessoa();
        Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataNascimento());

        p.setNome(dto.getNome());
        p.setDataNascimento(dataNascimento);

        repo.save(p);
        return p;
    }

    public Pessoa editarPessoa(Pessoa p, PessoaCadastroDTO dto) throws ParseException {
        if (dto.getNome() != null) {
            p.setNome(dto.getNome());
        }

        if (dto.getDataNascimento() != null) {
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataNascimento());
            p.setDataNascimento(dataNascimento);
        }

        repo.save(p);
        return p;
    }

    public Pessoa cadastraPessoaComEndereco(PessoaCadastroDTO dto) throws ParseException {
        Pessoa p = criaPessoa(dto);
        enderecoService.adicionaEndereco(p, dto.getEndereco());

        return p;
    }

    public Pessoa findById(Long id) {
        Optional<Pessoa> p = repo.findById(id);

        if (p.isPresent()) {
            List<Endereco> enderecos = enderecoService.listEnderecosByPessoa(id);
            p.get().setEnderecos(enderecos);

            return p.get();
        } else {
            return null;
        }
    }

    public List<Pessoa> listAll() {
        List<Pessoa> pessoas = (List<Pessoa>) repo.findAll();
        pessoas.forEach(p -> {
            List<Endereco> enderecos = enderecoService.listEnderecosByPessoa(p.getId());
            p.setEnderecos(enderecos);
        });

        return pessoas;
    }

}
