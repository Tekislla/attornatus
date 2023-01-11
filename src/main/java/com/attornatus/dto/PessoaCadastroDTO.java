package com.attornatus.dto;

import java.util.Date;

public class PessoaCadastroDTO {
    private String nome;
    private String dataNascimento;
    private EnderecoCadastroDTO endereco;

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public EnderecoCadastroDTO getEndereco() {
        return endereco;
    }
}