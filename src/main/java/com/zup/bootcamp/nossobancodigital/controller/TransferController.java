package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.request.TransferRequest;
import com.zup.bootcamp.nossobancodigital.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transferencia")
public class TransferController {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<?> receiveTransfer(@Valid @RequestBody TransferRequest transferRequest){
        logger.info("Transferência recebida");
        transferService.receiveTransfer(transferRequest);
        return ResponseEntity.ok().build();
    }

}
