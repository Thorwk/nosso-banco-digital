package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.request.*;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import com.zup.bootcamp.nossobancodigital.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequest clientRequest) {
        logger.info("Requisição para cadastro do cliente");
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, clientService.saveClient(clientRequest)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> saveEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest, @PathVariable("id") String id){
        logger.info("Requisição para cadastro do endereço");
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, clientService.saveEndereco(enderecoRequest, id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable("id") String id){
        logger.info("Requisição para visualizar dados da proposta");
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/aceite/{id}")
    public ResponseEntity<?> acceptedClient(@Valid @RequestBody AceiteRequest aceite, @PathVariable("id") String id) {
        logger.info("Requisição de aceite do cliente");
        return ResponseEntity.ok(clientService.acceptedClient(aceite, id));
    }

    @PutMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LoginRequest login) throws ValidationException {
        logger.info("Requisição de login");
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION, clientService.logIn(login)).build();
    }

    @PutMapping("/login/{id}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody SenhaRequest senha, @PathVariable("id") String id) throws ValidationException {
        logger.info("Requisição para cadastro da senha");
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION, clientService.changePassword(id, senha)).build();
    }

}
