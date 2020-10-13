package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.TransferEntity;
import com.zup.bootcamp.nossobancodigital.repository.TransferRepository;
import com.zup.bootcamp.nossobancodigital.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired @Lazy
    private ContaService contaService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String receiveTransfer(TransferRequest transferRequest){
        if(transferRepository.existsById(transferRequest.getCodigoTransfer())){
            throw new EntityExistsException("Transferência já realizada!");
        }
        TransferEntity transfer = new TransferEntity(transferRequest);
        transferRepository.save(transfer);
        contaService.updateSaldo(transferRequest);
        return "Sucesso";
    }

}
