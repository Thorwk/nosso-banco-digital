package com.zup.bootcamp.nossobancodigital.entity;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ContaEntity {

    @Id
    @Column(name = "id_conta")
    private String id;

    private String agencia;

    private String conta;

    private int codigoBanco;

    private double saldo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente", referencedColumnName = "id_client")
    private ClientEntity cliente;

    public ContaEntity() { }

    public ContaEntity(ClientEntity cliente) {
        this.id = UUID.randomUUID().toString();
        this.agencia = generateRandomNumericString(4);
        this.conta = generateRandomNumericString(8);
        this.codigoBanco = 341;
        this.saldo = 0;
        this.cliente = cliente;
    }

    private String generateRandomNumericString(int size){
        return RandomStringUtils.random(size,false,true);
    }

    public void updateSaldo(double transfer){
        this.saldo += transfer;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getAgencia() { return agencia; }

    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getConta() { return conta; }

    public void setConta(String conta) { this.conta = conta; }

    public int getCodigoBanco() { return codigoBanco; }

    public void setCodigoBanco(int codigoBanco) { this.codigoBanco = codigoBanco; }

    public double getSaldo() { return saldo; }

    public void setSaldo(double saldo) { this.saldo = saldo; }

    public ClientEntity getCliente() { return cliente; }

    public void setCliente(ClientEntity cliente) { this.cliente = cliente; }
}
