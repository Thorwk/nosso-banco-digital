package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.entity.TransferEntity;
import com.zup.bootcamp.nossobancodigital.repository.TransferRepository;
import com.zup.bootcamp.nossobancodigital.request.TransferRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.NoSuchElementException;

@Service
public class TransferService {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    @Autowired
    private TransferRepository transferRepository;

    @Autowired @Lazy
    private ContaService contaService;

    @Async
    public void receiveTransfer(TransferRequest transferRequest) {
        if(contaService.existsByConta(transferRequest.getContaDestino())) {
            if (transferRepository.existsById(transferRequest.getCodigoTransfer())) {
                logger.info("Transferência já realizada!");
            } else {
                updateSaldo(transferRequest);
            }
        }else{
            logger.info("Conta não existe");
        }
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void updateSaldo(TransferRequest transferRequest){
        TransferEntity transfer = new TransferEntity(transferRequest);
        transferRepository.save(transfer);
        logger.info("Transferência salva");
        contaService.updateSaldo(transferRequest);
    }

}
