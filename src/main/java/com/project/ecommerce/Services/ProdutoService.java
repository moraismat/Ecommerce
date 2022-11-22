package com.project.ecommerce.Services;


import com.project.ecommerce.DTO.Request.ProdutoRequest;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.models.Categoria;
import com.project.ecommerce.models.Produto;
import com.project.ecommerce.repositories.CategoriaRepository;
import com.project.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto addProduto(ProdutoRequest produtoRequest) throws Exception {
        Categoria categoria = categoriaRepository.findById(produtoRequest.categoria_id).get();
        if(categoria == null){
            throw new Exception("Categoria: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoRequest.categoria_id);
        }

        Produto oldProduto = produtoRepository.findByName(produtoRequest.name);
        if (oldProduto != null){
            throw new Exception("Categoria: "
                    + Constantes.PRODUTO_JA_CADASTRADO
                    + produtoRequest.name);
        }

        Produto newProduto = new Produto(null, produtoRequest.name, produtoRequest.price);
        newProduto.setCategoria(categoria);

        categoria.getProdutos().add(newProduto);
        categoriaRepository.save(categoria);
        produtoRepository.save(newProduto);

        return newProduto;
    }
}
