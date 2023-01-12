package com.attornatus.service;

import com.attornatus.dto.EnderecoCadastroDTO;
import com.attornatus.entity.Endereco;
import com.attornatus.entity.Pessoa;
import com.attornatus.repository.EnderecoRepository;
import com.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repo;

    @Autowired
    PessoaRepository pessoaRepository;

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

    public Endereco adicionaEndereco(Pessoa p, EnderecoCadastroDTO dto) {
        Endereco e = criaEndereco(dto, p.getId());

        if (p.getEnderecos() == null || p.getEnderecos().isEmpty()) {
            p.setEnderecos(Arrays.asList(e));
        } else {
            p.getEnderecos().forEach(endereco -> endereco.setEnderecoPrincipal(false));
            p.getEnderecos().add(e);
        }

        pessoaRepository.save(p);
        return e;
    }

    public List<Endereco> listEnderecosByPessoa(Long pessoaId) {
        return repo.findByPessoaId(pessoaId);
    }

    public Endereco findEnderecoPrincipal(Long pessoaId) {
        return repo.findByPessoaIdAndEnderecoPrincipal(pessoaId, true);
    }
}
