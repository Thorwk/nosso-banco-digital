package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import com.zup.bootcamp.nossobancodigital.response.ContaResponse;
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

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ClientService clientService;

    @Autowired @Lazy
    private ContaService contaService;

    public void sendSimpleMessage(String id, boolean aceite){

        ClientResponse client = clientService.findById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.nossobancodigital@gmail.com");
        message.setTo(client.getEmail());
        message.setSubject("Conta - Nosso Banco Digital");

        if(aceite){
            ContaResponse contaResponse = contaService.createAccount(id);
            message.setText("Sua conta foi criada com sucesso!\n" + contaResponse.toString());
        }else{
            message.setText("Por favorzinho, aceita a proposta! Te pago um sorvete!");
        }

        javaMailSender.send(message);

    }

    @Async
    public void sendToken(String token, String id){
        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/login")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        ClientResponse client = clientService.findById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.nossobancodigital@gmail.com");
        message.setTo(client.getEmail());
        message.setSubject("Token de Primeiro Acesso - Nosso Banco Digital");
        message.setText("Utilize o token abaixo para cadastrar sua senha.\n\n"
                        + "Token: " + client.getToken() + "\n\n"
                        + "Link para cadastro da senha: " + location);

        javaMailSender.send(message);

    }

}
