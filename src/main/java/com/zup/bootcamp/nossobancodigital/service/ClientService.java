package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.request.*;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired @Lazy
    private ContaService contaService;

    @Autowired @Lazy
    private EmailService emailService;

    @Autowired @Lazy
    private TokenService tokenService;

    public String saveClient(ClientRequest clientRequest) {
        verifyEmail(clientRequest.getEmail());
        verifyCpf(clientRequest.getCpf());
        ClientEntity client = new ClientEntity(clientRequest);
        client.setEtapa(1);
        clientRepository.save(client);

        logger.info("Cliente salvo com sucesso");

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUriString();

        logger.info("Header location retornado para o controller");

        return location;
    }

    public String saveEndereco(EnderecoRequest enderecoRequest, String id){
        verifyStep(id, 1);
        ClientEntity client = clientRepository.findById(id).get();
        client.setCep(enderecoRequest.getCep());
        client.setRua(enderecoRequest.getRua());
        client.setBairro(enderecoRequest.getBairro());
        client.setComplemento(enderecoRequest.getComplemento());
        client.setCidade(enderecoRequest.getCidade());
        client.setEstado(enderecoRequest.getEstado());
        client.setEtapa(2);

        clientRepository.save(client);

        logger.info("Endereço salvo com sucesso");

        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/files")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        logger.info("Header location retornado para o controller");

        return location;
    }

    public ClientResponse findById(String id){
        verifyStep(id, 3);
        ClientResponse clientResponse = new ClientResponse(clientRepository.findById(id).get());
        logger.info("Dados da proposta retornados para confirmação");
        return clientResponse;
    }

    public ClientEntity findEntityById(String id){
        return clientRepository.findById(id).get();
    }

    public void saveEntity(ClientEntity client){ clientRepository.save(client);}

    public String acceptedClient(AceiteRequest aceite, String id) {
        ClientEntity client = clientRepository.findById(id).get();
        String mensagem;
        if(aceite.getAceite().equals("1")){
            client.setAceiteCliente(true);
            clientRepository.save(client);
            logger.info("Aceite positivo do cliente");
            mensagem = "Muito obrigado por sua aplicação! \n" +
                    "Um email será enviado em breve com mais informações.";
        }else{
            logger.info("Aceite negativo do cliente");
            mensagem = "É uma pena que não tenha aceito :(. \n" +
                    "Um email será enviado com uma proposta que você não vai poder recusar.";
        }

        contaService.acceptedExternal(id);

        return mensagem;
    }

    public String logIn(LoginRequest login) throws ValidationException{
        ClientEntity client = clientRepository.findByEmail(login.getEmail());
        if(client == null){
            logger.info("Nenhum cliente encontrado para o email informado");
            throw new NoSuchElementException("Cliente não cadastrado!");
        }
        if(client.isPrimeiroAcesso()) {
            return firstAccess(client, login);
        }else{
            // criar sessão
            if(client.getEmail().equals(login.getEmail()) && client.getToken().getConteudo().equals(login.getSenha())){
                return null;
            }else{
                throw new ValidationException("Email ou senha incorretos!");
            }
        }
    }

    public String firstAccess(ClientEntity client, LoginRequest login) throws ValidationException {
        if (client.getEmail().equals(login.getEmail()) && client.getCpf().equals(login.getSenha())) {
            logger.info("Primeiro acesso para geração do token");
            client.setPrimeiroAcesso(false);
            client.setToken(tokenService.createToken(client.getId()));
            clientRepository.save(client);
            logger.info("Token criado com sucesso");
            emailService.sendToken(client.getId());

            String location = ServletUriComponentsBuilder
                    .fromUriString("http://localhost:8080/login")
                    .path("/{id}")
                    .buildAndExpand(client.getId())
                    .toUriString();
            logger.info("Header location retornado para o controller");

            return location;
        } else {
            logger.info("Email ou senha incorretos");
            throw new ValidationException("Email ou senha incorretos!");
        }
    }

    public String changePassword(String id, SenhaRequest senha) throws ValidationException {
        ClientEntity client = clientRepository.findById(id).get();
        if(verifyToken(client, senha)){
            client.getToken().setUsado(true);
            client.setSenha(BCrypt.hashpw(senha.getSenha(), BCrypt.gensalt()));
            clientRepository.save(client);
            logger.info("Nova senha salva com sucesso");

            String location = ServletUriComponentsBuilder
                    .fromUriString("http://localhost:8080")
                    .path("/login")
                    .buildAndExpand(client.getId())
                    .toUriString();

            logger.info("Header location retornado para o controller");

            emailService.passwordChanged(id);

            return location;
        }else{
            throw new ValidationException("Token inválido!");
        }
    }

    public boolean verifyToken(ClientEntity client, SenhaRequest senha) {
        if(!tokenService.existsByConteudo(senha.getToken())){
            return false;
        }else if(tokenService.isExpired(senha.getToken())) {
            logger.info("Token expirado");
            return false;
        }else if(tokenService.isUsed(senha.getToken())){
            logger.info("Token já foi utilizado");
            return false;
        }else if(!client.getToken().getConteudo().equals(senha.getToken())){
            logger.info("Token inválido");
            return false;
        }else{
            logger.info("Token válido");
            return true;
        }
    }

    public void verifyStep(String id, int etapa) throws EntityNotFoundException {
        if(clientRepository.findById(id).get().getEtapa() < etapa){
            logger.info("Etapa anterior não foi completada");
            throw new EntityNotFoundException("Complete os passos anteriores!");
        }
    }

    public void verifyEmail(String email) throws EntityExistsException{
        if(clientRepository.findByEmail(email)!=null) {
            logger.info("Email já cadastrado no sistema");
            throw new EntityExistsException("Email já cadastrado!");
        }
    }

    public void verifyCpf(String cpf) throws EntityExistsException{
        if(clientRepository.findByCpf(cpf)!=null) {
            logger.info("CPF já cadastrado no sistema");
            throw new EntityExistsException("Cpf já cadastrado!");
        }
    }

}
