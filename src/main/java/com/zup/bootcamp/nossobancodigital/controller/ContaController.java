package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.request.LoginRequest;
import com.zup.bootcamp.nossobancodigital.response.ContaResponse;
import com.zup.bootcamp.nossobancodigital.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/{conta}")
    public ResponseEntity<ContaResponse> getConta(@PathVariable("conta") String conta){
        return ResponseEntity.ok().body(contaService.getConta(conta));
    }

}
