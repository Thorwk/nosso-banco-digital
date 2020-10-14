package com.zup.bootcamp.nossobancodigital.response;

import com.zup.bootcamp.nossobancodigital.entity.ContaEntity;

public class ContaResponse {

    private String id;

    private String agencia;

    private String conta;

    private int codigoBanco;

    private double saldo;

    private String nomeCompleto;

    public ContaResponse(ContaEntity conta) {
        this.id = conta.getId();
        this.agencia = conta.getAgencia();
        this.conta = conta.getConta();
        this.codigoBanco = conta.getCodigoBanco();
        this.saldo = conta.getSaldo();
        this.nomeCompleto = conta.getCliente().getNome() + " " + conta.getCliente().getSobrenome();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    @Override
    public String toString() {
        return  "Agência: " + agencia + "\n" +
                "Conta: " + conta + "\n" +
                "Código do Banco: " + codigoBanco;
    }
}
