package com.project.ecommerce.Services;

import com.project.ecommerce.DTO.Request.CategoriaCreateRequest;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.models.Categoria;
import com.project.ecommerce.repositories.CategoriaRepository;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() throws Exception {
        List<Categoria> listCategoria = categoriaRepository.findAll();
        if(listCategoria.isEmpty()){
            throw new Exception(
                    Constantes.NENHUM_REGISTRO_ENCONTRADO + Categoria.class
            );
        }

        return listCategoria;
    }

    public Categoria findById(int id) throws Exception {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()){
           throw new Exception(Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID + id);
        }

        return categoria.get();
    }

    public Categoria createNewCategoria(CategoriaCreateRequest categoriaCreateRequest) throws Exception {
        Categoria oldCategoria = categoriaRepository.findByName(categoriaCreateRequest.getName());
        if(oldCategoria != null){
            throw new Exception(Constantes.CATEGORIA_JA_CADASTRADA + categoriaCreateRequest.getName());
        }

        Categoria newCategoria = new Categoria(null, categoriaCreateRequest.getName());
        newCategoria = categoriaRepository.save(newCategoria);

        return newCategoria;
    }
}
