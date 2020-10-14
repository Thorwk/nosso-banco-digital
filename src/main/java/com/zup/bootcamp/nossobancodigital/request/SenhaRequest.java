package com.zup.bootcamp.nossobancodigital.request;

import com.zup.bootcamp.nossobancodigital.validation.Password;

import javax.validation.constraints.NotBlank;

public class SenhaRequest {

    @NotBlank(message = "Token obrigatório!")
    private String token;

    @Password
    private String senha;

    public SenhaRequest() { }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
