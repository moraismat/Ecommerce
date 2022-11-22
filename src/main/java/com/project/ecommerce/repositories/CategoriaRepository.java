package com.project.ecommerce.repositories;

import com.project.ecommerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Transactional(readOnly=true)
    Categoria findByName(String name);
}
