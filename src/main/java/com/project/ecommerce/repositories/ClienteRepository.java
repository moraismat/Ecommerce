package com.project.ecommerce.repositories;

import com.project.ecommerce.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    Cliente findByCPF(Long cpf);

    @Transactional(readOnly = true)
    Cliente findByEMAIL(String email);

}
