package com.zup.bootcamp.nossobancodigital.request;

import javax.validation.constraints.NotBlank;

public class SenhaRequest {

    private String senha;

    public SenhaRequest() { }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
