package com.attornatus.repository;

import com.attornatus.entity.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    public List<Endereco> findByPessoaId(Long pessoaId);
    public Endereco findByPessoaIdAndEnderecoPrincipal(Long pessoaId, Boolean enderecoPrincipal);

}
