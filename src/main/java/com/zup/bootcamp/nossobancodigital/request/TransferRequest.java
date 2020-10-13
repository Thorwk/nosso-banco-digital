package com.zup.bootcamp.nossobancodigital.request;

import com.zup.bootcamp.nossobancodigital.validation.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferRequest {

    @NotNull(message = "Valor da transferência é obrigatório!")
    private double valorTransferencia;

    @Date
    private String dataRealizacao;

    @NotBlank(message = "Documento de origem é obrigatório!")
    private String docOrigem;

    @NotBlank(message = "Banco de origem é obrigatório!")
    private String bancoOrigem;

    @NotBlank(message = "Conta de origem é obrigatório!")
    private String contaOrigem;

    @NotBlank(message = "Agência de origem é obrigatório!")
    private String agenciaOrigem;

    @NotBlank(message = "Código da transferência é obrigatório!")
    private String codigoTransfer;

    @NotBlank(message = "Conta de destino é obrigatório!")
    private String contaDestino;

    @NotBlank(message = "Agência de destino é obrigatório!")
    private String agenciaDestino;

    public TransferRequest() { }

    public double getValorTransferencia() { return valorTransferencia; }

    public void setValorTransferencia(double valorTransferencia) { this.valorTransferencia = valorTransferencia; }

    public String getDataRealizacao() { return dataRealizacao; }

    public void setDataRealizacao(String dataRealizacao) { this.dataRealizacao = dataRealizacao; }

    public String getDocOrigem() { return docOrigem; }

    public void setDocOrigem(String docOrigem) { this.docOrigem = docOrigem; }

    public String getBancoOrigem() { return bancoOrigem; }

    public void setBancoOrigem(String bancoOrigem) { this.bancoOrigem = bancoOrigem; }

    public String getContaOrigem() { return contaOrigem; }

    public void setContaOrigem(String contaOrigem) { this.contaOrigem = contaOrigem; }

    public String getAgenciaOrigem() { return agenciaOrigem; }

    public void setAgenciaOrigem(String agenciaOrigem) { this.agenciaOrigem = agenciaOrigem; }

    public String getCodigoTransfer() { return codigoTransfer; }

    public void setCodigoTransfer(String codigoTransfer) { this.codigoTransfer = codigoTransfer; }

    public String getContaDestino() { return contaDestino; }

    public void setContaDestino(String contaDestino) { this.contaDestino = contaDestino; }

    public String getAgenciaDestino() { return agenciaDestino; }

    public void setAgenciaDestino(String agenciaDestino) { this.agenciaDestino = agenciaDestino; }
}
