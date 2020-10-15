package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.TokenEntity;
import com.zup.bootcamp.nossobancodigital.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Lazy
@Service
public class TokenService {

    // em horas
    public static final int VALIDADE_TOKEN = 1;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public TokenEntity createToken(String id){
        TokenEntity tokenEntity = new TokenEntity();
        tokenRepository.save(tokenEntity);
        return tokenEntity;
    }

    public boolean isExpired(String conteudo){
        TokenEntity token = tokenRepository.findByConteudo(conteudo);
        if(LocalDateTime.now().minusHours(VALIDADE_TOKEN).isAfter(token.getCriado())){
            return true;
        }else{
            return false;
        }
    }

    public boolean isUsed(String conteudo){
        TokenEntity token = tokenRepository.findByConteudo(conteudo);
        return token.isUsado();
    }

}
