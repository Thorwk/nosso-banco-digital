package com.zup.bootcamp.nossobancodigital.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Campo obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "Campo obrigatório!")
    private String senha;

    public LoginRequest() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
