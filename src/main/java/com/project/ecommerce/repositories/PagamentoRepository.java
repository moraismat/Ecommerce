package com.project.ecommerce.repositories;

import com.project.ecommerce.models.Produto;
import com.project.ecommerce.models.enums.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
