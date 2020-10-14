package com.zup.bootcamp.nossobancodigital.repository;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    ClientEntity findByEmail(String email);
    ClientEntity findByCpf(String cpf);
}
