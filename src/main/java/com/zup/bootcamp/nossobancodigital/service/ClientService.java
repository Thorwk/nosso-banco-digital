package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.request.ClientRequest;
import com.zup.bootcamp.nossobancodigital.request.EnderecoRequest;
import com.zup.bootcamp.nossobancodigital.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String saveClient(ClientRequest clientRequest) {
        verifyEmail(clientRequest.getEmail());
        verifyCpf(clientRequest.getCpf());
        ClientEntity client = new ClientEntity(clientRequest);
        client.setEtapa(1);
        clientRepository.save(client);

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUriString();

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

        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/files")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return location;
    }

    public ClientResponse findById(String id){
        verifyStep(id, 3);
        ClientResponse clientResponse = new ClientResponse(clientRepository.findById(id).get());
        return clientResponse;
    }

    public void verifyStep(String id, int etapa) throws EntityNotFoundException {
        if(clientRepository.findById(id).get().getEtapa() < etapa){
            throw new EntityNotFoundException("Complete os passos anteriores!");
        }
    }

    public void verifyEmail(String email) throws EntityExistsException{
        if(clientRepository.findByEmail(email)!=null) {
            throw new EntityExistsException("Email já cadastrado!");
        }
    }

    public void verifyCpf(String cpf) throws EntityExistsException{
        if(clientRepository.findByCpf(cpf)!=null) {
            throw new EntityExistsException("Cpf já cadastrado!");
        }
    }

}
