package com.zup.bootcamp.nossobancodigital.response;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;

import java.time.LocalDate;

public class ClientResponse {

    private String id;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;

    private LocalDate nascimento;

    private String cep;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String estado;

    private String arquivoCpf;

    public ClientResponse(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.nome = clientEntity.getNome();
        this.sobrenome = clientEntity.getSobrenome();
        this.email = clientEntity.getEmail();
        this.cpf = clientEntity.getCpf();
        this.nascimento = clientEntity.getNascimento();
        this.cep = clientEntity.getCep();
        this.rua = clientEntity.getRua();
        this.bairro = clientEntity.getBairro();
        this.complemento = clientEntity.getComplemento();
        this.cidade = clientEntity.getCidade();
        this.estado = clientEntity.getEstado();
        this.arquivoCpf = clientEntity.getArquivoCPF();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArquivoCpf() { return arquivoCpf; }

    public void setArquivoCpf(String arquivoCpf) { this.arquivoCpf = arquivoCpf; }

    @Override
    public String toString() {
        return "ClientResponse{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nascimento=" + nascimento +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", arquivoCpf='" + arquivoCpf + '\'' +
                '}';
    }
}
