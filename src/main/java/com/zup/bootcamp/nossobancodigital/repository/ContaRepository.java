package com.zup.bootcamp.nossobancodigital.repository;

import com.zup.bootcamp.nossobancodigital.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, String> {
    boolean existsByConta(String conta);
    ContaEntity findByConta (String conta);
}
