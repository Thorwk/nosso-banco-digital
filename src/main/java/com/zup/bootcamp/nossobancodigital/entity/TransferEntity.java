package com.zup.bootcamp.nossobancodigital.entity;

import com.zup.bootcamp.nossobancodigital.request.TransferRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class TransferEntity {

    @Id
    @Column(name = "id_transferencia")
    private String id;
    private double valorTransferencia;
    private LocalDate dataRealizacao;
    private String docOrigem;
    private String bancoOrigem;
    private String contaOrigem;
    private String agenciaOrigem;
    private String contaDestino;
    private String agenciaDestino;

    public TransferEntity() { }

    public TransferEntity(TransferRequest transferRequest) {
        this.id = transferRequest.getCodigoTransfer();
        this.valorTransferencia = transferRequest.getValorTransferencia();
        this.dataRealizacao = LocalDate.parse(transferRequest.getDataRealizacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.docOrigem = transferRequest.getDocOrigem();
        this.bancoOrigem = transferRequest.getBancoOrigem();
        this.contaOrigem = transferRequest.getContaOrigem();
        this.agenciaOrigem = transferRequest.getAgenciaOrigem();
        this.contaDestino = transferRequest.getContaDestino();
        this.agenciaDestino = transferRequest.getAgenciaDestino();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public double getValorTransferencia() { return valorTransferencia; }

    public void setValorTransferencia(double valorTransferencia) { this.valorTransferencia = valorTransferencia; }

    public LocalDate getDataRealizacao() { return dataRealizacao; }

    public void setDataRealizacao(LocalDate dataRealizacao) { this.dataRealizacao = dataRealizacao; }

    public String getDocOrigem() { return docOrigem; }

    public void setDocOrigem(String docOrigem) { this.docOrigem = docOrigem; }

    public String getBancoOrigem() { return bancoOrigem; }

    public void setBancoOrigem(String bancoOrigem) { this.bancoOrigem = bancoOrigem; }

    public String getContaOrigem() { return contaOrigem; }

    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getAgenciaOrigem() { return agenciaOrigem; }

    public void setAgenciaOrigem(String agenciaOrigem) { this.agenciaOrigem = agenciaOrigem; }

    public String getContaDestino() { return contaDestino; }

    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }

    public String getAgenciaDestino() { return agenciaDestino; }

    public void setAgenciaDestino(String agenciaDestino) { this.agenciaDestino = agenciaDestino; }

}
