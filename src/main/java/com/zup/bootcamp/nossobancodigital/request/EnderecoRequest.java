package com.zup.bootcamp.nossobancodigital.request;

import com.zup.bootcamp.nossobancodigital.validation.Cep;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

    @Cep
    private String cep;

    @NotBlank(message = "Rua é obrigatório!")
    private String rua;

    @NotBlank(message = "Bairro é obrigatório!")
    private String bairro;

    @NotBlank(message = "Complemento é obrigatório!")
    private String complemento;

    @NotBlank(message = "Cidade é obrigatório!")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório!")
    private String estado;

    public EnderecoRequest() { }

    public String getCep() { return cep; }

    public void setCep(String cep) { this.cep = cep; }

    public String getRua() { return rua; }

    public void setRua(String rua) { this.rua = rua; }

    public String getBairro() { return bairro; }

    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getComplemento() { return complemento; }

    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

}
