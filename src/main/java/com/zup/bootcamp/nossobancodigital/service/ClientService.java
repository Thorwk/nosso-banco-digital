package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.controller.CustomExceptionHandler;
import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends CustomExceptionHandler {

    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse saveClient(ClientRequest clientRequest) throws Exception {
        verifyEmail(clientRequest.getEmail());
        ClientEntity client = new ClientEntity(clientRequest);
        clientRepository.save(client);
        return new ClientResponse(client.getId());
    }

    public void verifyEmail(String email) throws Exception{
        if(clientRepository.findByEmail(email)!=null) {
            throw new Exception("Email já cadastrado!");
        }
    }

}
