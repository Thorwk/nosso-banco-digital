package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.response.ContaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Lazy
@Service
public class EmailService {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    private final String EMAIL = "noreply.nossobancodigital@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ClientService clientService;

    @Autowired @Lazy
    private ContaService contaService;

    public void sendSimpleMessage(String id){

        ClientEntity client = clientService.findEntityById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL);
        message.setTo(client.getEmail());
        message.setSubject("Conta - Nosso Banco Digital");

        if(client.isLiberado()){
            ContaResponse contaResponse = contaService.createAccount(id);
            message.setText("Sua conta foi criada com sucesso!\n" + contaResponse.toString());
            logger.info("Mensagem com os dados da conta");
        }else if(!client.isAceiteCliente()){
            message.setText("Por favorzinho, aceita a proposta! Te pago um sorvete!");
            logger.info("Mensagem implorando aceite do cliente");
        }

        javaMailSender.send(message);
        logger.info("Email enviado");

    }

    @Async
    public void sendToken(String id){
        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/login")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        ClientEntity client = clientService.findEntityById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL);
        message.setTo(client.getEmail());
        message.setSubject("Token de Primeiro Acesso - Nosso Banco Digital");
        message.setText("Utilize o token abaixo para cadastrar sua senha.\n\n"
                        + "Token: " + client.getToken().getConteudo() + "\n\n"
                        + "Link para cadastro da senha: " + location + " \n\n"
                        + "Obs.: O token tem validade de " + TokenService.VALIDADE_TOKEN + " hora.");

        javaMailSender.send(message);
        logger.info("Token enviado para o email cadastrado");

    }
    @Async
    public void passwordChanged(String id){
        ClientEntity client = clientService.findEntityById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EMAIL);
        message.setTo(client.getEmail());
        message.setSubject("Senha alterada - Nosso Banco Digital");
        message.setText("A sua senha foi alterada com sucesso!");

        javaMailSender.send(message);
        logger.info("Email informando mudança de senha");
    }

}
