package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import com.zup.bootcamp.nossobancodigital.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> saveClient(@Valid @RequestBody ClientRequest clientRequest){
        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientService.saveClient(clientRequest).getId())
                .toUriString();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).build();
    }

}
