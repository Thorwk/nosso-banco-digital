package com.zup.bootcamp.nossobancodigital.request;

import com.zup.bootcamp.nossobancodigital.validation.Adult;
import com.zup.bootcamp.nossobancodigital.validation.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientRequest {

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório!")
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "CNH é obrigatória!")
    @Size(min = 11, max = 11, message = "CNH inválida!")
    private Long cnh;

    @Adult
    @Date
    private String nascimento;

    public ClientRequest(String id, String nome, String sobrenome, String email, Long cnh, String nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cnh = cnh;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCnh() {
        return cnh;
    }

    public void setCnh(Long cnh) {
        this.cnh = cnh;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
