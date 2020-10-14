package com.zup.bootcamp.nossobancodigital.entity;

import com.zup.bootcamp.nossobancodigital.request.ClientRequest;

import javax.persistence.*;
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

    private String cpf;

    private LocalDate nascimento;

    private String cep;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String estado;

    private String arquivoCPF;

    private boolean aceiteCliente;

    private boolean aceiteExterno;

    private boolean liberado;

    private int etapa = 0;

    private boolean primeiroAcesso;

    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token", referencedColumnName = "id_token")
    private TokenEntity token;

    @OneToOne(mappedBy = "cliente")
    private ContaEntity conta;

    public ClientEntity() { }

    public ClientEntity(ClientRequest clientRequest) {
        this.id = UUID.randomUUID().toString();
        this.nome = clientRequest.getNome();
        this.sobrenome = clientRequest.getSobrenome();
        this.email = clientRequest.getEmail();
        this.cpf = clientRequest.getCpf();
        this.nascimento = LocalDate.parse(clientRequest.getNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.aceiteCliente = false;
        this.aceiteExterno = false;
        this.liberado = false;
        this.primeiroAcesso = true;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getNascimento() { return nascimento; }

    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }

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

    public String getArquivoCPF() { return arquivoCPF; }

    public void setArquivoCPF(String arquivoCPF) { this.arquivoCPF = arquivoCPF; }

    public boolean isAceiteCliente() { return aceiteCliente; }

    public void setAceiteCliente(boolean aceiteCliente) { this.aceiteCliente = aceiteCliente; }

    public boolean isAceiteExterno() { return aceiteExterno; }

    public void setAceiteExterno(boolean aceiteExterno) { this.aceiteExterno = aceiteExterno; }

    public boolean isLiberado() { return liberado; }

    public void setLiberado(boolean liberado) { this.liberado = liberado; }

    public int getEtapa() { return etapa; }

    public void setEtapa(int etapa) { this.etapa = etapa; }

    public boolean isPrimeiroAcesso() { return primeiroAcesso; }

    public void setPrimeiroAcesso(boolean primeiroAcesso) { this.primeiroAcesso = primeiroAcesso; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public TokenEntity getToken() { return token; }

    public void setToken(TokenEntity token) { this.token = token; }

    public ContaEntity getConta() { return conta; }

    public void setConta(ContaEntity conta) { this.conta = conta; }
}
