package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.TransferEntity;
import com.zup.bootcamp.nossobancodigital.repository.TransferRepository;
import com.zup.bootcamp.nossobancodigital.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.LockModeType;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired @Lazy
    private ContaService contaService;

    @Async
    public void receiveTransfer(TransferRequest transferRequest){
        if(transferRepository.existsById(transferRequest.getCodigoTransfer())){
            throw new EntityExistsException("Transferência já realizada!");
        }
        updateSaldo(transferRequest);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void updateSaldo(TransferRequest transferRequest){
        TransferEntity transfer = new TransferEntity(transferRequest);
        transferRepository.save(transfer);
        contaService.updateSaldo(transferRequest);
    }

}
