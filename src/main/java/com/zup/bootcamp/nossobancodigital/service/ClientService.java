package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.controller.CustomExceptionHandler;
import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.request.EnderecoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class ClientService extends CustomExceptionHandler {

    @Autowired
    private ClientRepository clientRepository;

    public String saveClient(ClientRequest clientRequest) throws Exception {
        verifyEmail(clientRequest.getEmail());
        ClientEntity client = new ClientEntity(clientRequest);
        clientRepository.save(client);

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUriString();

        return location;
    }

    public String saveEndereco(EnderecoRequest enderecoRequest, String id){
        ClientEntity client = clientRepository.findById(id).get();
        client.setCep(enderecoRequest.getCep());
        client.setRua(enderecoRequest.getRua());
        client.setBairro(enderecoRequest.getBairro());
        client.setComplemento(enderecoRequest.getComplemento());
        client.setCidade(enderecoRequest.getCidade());
        client.setEstado(enderecoRequest.getEstado());

        clientRepository.save(client);

        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return location;
    }

    public List<ClientEntity> findAll(){
        return clientRepository.findAll();
    }

    public void verifyEmail(String email) throws Exception{
        if(clientRepository.findByEmail(email)!=null) {
            throw new Exception("Email já cadastrado!");
        }
    }

}
