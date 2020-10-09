package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.request.EnderecoRequest;
import com.zup.bootcamp.nossobancodigital.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController extends CustomExceptionHandler {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequest clientRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, clientService.saveClient(clientRequest)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> saveEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest, @PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, clientService.saveEndereco(enderecoRequest, id)).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> listClients(){
        return ResponseEntity.ok(clientService.findAll());
    }

}
