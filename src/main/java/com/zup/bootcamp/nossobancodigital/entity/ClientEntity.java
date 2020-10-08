package com.zup.bootcamp.nossobancodigital.entity;

import com.zup.bootcamp.nossobancodigital.request.ClientRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class ClientEntity {

    @Id
    @Column(name = "id_client")
    private String id;

    private String nome;

    private String sobrenome;

    private String email;

    private Long cnh;

    private LocalDate nascimento;

    public ClientEntity() { }

    public ClientEntity(ClientRequest clientRequest) {
        this.id = UUID.randomUUID().toString();
        this.nome = clientRequest.getNome();
        this.sobrenome = clientRequest.getSobrenome();
        this.email = clientRequest.getEmail();
        this.cnh = clientRequest.getCnh();
        this.nascimento = LocalDate.parse(clientRequest.getNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Long getCnh() { return cnh; }

    public void setCnh(Long cnh) { this.cnh = cnh; }

    public LocalDate getNascimento() { return nascimento; }

    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }

}
