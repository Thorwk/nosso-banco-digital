package com.zup.bootcamp.nossobancodigital.request;

import com.zup.bootcamp.nossobancodigital.validation.Adult;
import com.zup.bootcamp.nossobancodigital.validation.Date;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ClientRequest {

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório!")
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "CPF é obrigatório!")
    @CPF(message = "CPF inválido!")
    private String cpf;

    @Date
    @Adult
    private String nascimento;

    public ClientRequest() { }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNascimento() { return nascimento; }

    public void setNascimento(String nascimento) { this.nascimento = nascimento; }

}
