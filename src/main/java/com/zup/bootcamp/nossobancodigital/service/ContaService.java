package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.entity.ContaEntity;
import com.zup.bootcamp.nossobancodigital.repository.ContaRepository;
import com.zup.bootcamp.nossobancodigital.request.TransferRequest;
import com.zup.bootcamp.nossobancodigital.response.ContaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Lazy
@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClientService clientService;

    @Autowired @Lazy
    private EmailService emailService;

    public ContaResponse createAccount(String id){
        ClientEntity client = clientService.findEntityById(id);
        ContaEntity conta = new ContaEntity(client);
        contaRepository.save(conta);
        return new ContaResponse(conta);
    }

    @Async
    @Retryable
    public void acceptedExternal(String id) throws InterruptedException {
        ClientEntity client = clientService.findEntityById(id);
        Thread.sleep(10000);
        if(client.isAceiteCliente()){
            client.setAceiteExterno(true);
            client.setLiberado(true);
            clientService.saveEntity(client);
            emailService.sendSimpleMessage(id, true);
        }else{
            emailService.sendSimpleMessage(id, false);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSaldo(TransferRequest transferRequest){
        contaRepository.existsByConta(transferRequest.getContaDestino());
        ContaEntity conta = contaRepository.findByConta(transferRequest.getContaDestino());
        conta.updateSaldo(transferRequest.getValorTransferencia());
        contaRepository.save(conta);
    }

    public ContaResponse getConta(String conta){
        return new ContaResponse(contaRepository.findByConta(conta));
    }

}
