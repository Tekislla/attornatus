package com.attornatus.service;

import com.attornatus.dto.PessoaCadastroDTO;
import com.attornatus.entity.Endereco;
import com.attornatus.entity.Pessoa;
import com.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        p.setEnderecos(new ArrayList<Endereco>());

        repo.save(p);
        return p;
    }

    public Pessoa editarPessoa(Long id, PessoaCadastroDTO dto) throws ParseException {
        Pessoa p = findById(id);

        if (dto.getNome() != null) {
            p.setNome(dto.getNome());
        }

        if (dto.getDataNascimento() != null) {
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDataNascimento());
            p.setDataNascimento(dataNascimento);
        }

        return p;
    }

    public Pessoa cadastraPessoaComEndereco(PessoaCadastroDTO dto) throws ParseException {
        Pessoa p = criaPessoa(dto);
        Endereco e = enderecoService.criaEndereco(dto.getEndereco(), p.getId());
        enderecoService.adicionaEndereco(p, e);

        return p;
    }

    public Pessoa findById(Long id) {
        return repo.findById(id).get();
    }

}
