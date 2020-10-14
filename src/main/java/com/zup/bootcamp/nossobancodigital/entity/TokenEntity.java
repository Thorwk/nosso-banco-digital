package com.zup.bootcamp.nossobancodigital.entity;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TokenEntity {

    @Id
    @Column(name = "id_token")
    private String id;

    private String conteudo;

    private LocalDateTime criado;

    private boolean usado;

    @OneToOne(mappedBy = "token")
    private ClientEntity cliente;

    public TokenEntity() {
        this.id = UUID.randomUUID().toString();
        this.conteudo = generateToken();
        this.criado = LocalDateTime.now();
        this.usado = false;
    }

    private String generateToken(){
        return RandomStringUtils.randomAlphanumeric(6);
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getConteudo() { return conteudo; }

    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public LocalDateTime getCriado() { return criado; }

    public void setCriado(LocalDateTime criado) { this.criado = criado; }

    public boolean isUsado() { return usado; }

    public void setUsado(boolean usado) { this.usado = usado; }
}
