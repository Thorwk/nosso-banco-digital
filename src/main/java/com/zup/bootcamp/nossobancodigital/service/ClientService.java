package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse saveClient(ClientRequest clientRequest){
        ClientEntity client = new ClientEntity(clientRequest);
        clientRepository.save(client);
        return new ClientResponse(client.getId());
    }

}
