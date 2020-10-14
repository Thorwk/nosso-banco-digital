package com.zup.bootcamp.nossobancodigital.repository;

import com.zup.bootcamp.nossobancodigital.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    TokenEntity findByConteudo(String conteudo);
}
