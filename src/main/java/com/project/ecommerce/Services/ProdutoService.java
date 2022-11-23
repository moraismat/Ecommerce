package com.project.ecommerce.Services;


import com.project.ecommerce.DTO.Request.CategoriaRequest;
import com.project.ecommerce.DTO.Request.ProdutoDeleteRequest;
import com.project.ecommerce.DTO.Request.ProdutoRequest;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.models.Categoria;
import com.project.ecommerce.models.Produto;
import com.project.ecommerce.repositories.CategoriaRepository;
import com.project.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Produto> findAll() throws Exception {
        List<Produto> lstProdutos = produtoRepository.findAll();
        if(lstProdutos.isEmpty()){
            throw new Exception("Produtos: "+Constantes.NENHUM_REGISTRO_ENCONTRADO);
        }

        return lstProdutos;
    }

    public Produto findById(int id) throws Exception {
        Produto produto = produtoRepository.findById(id).get();
        if (produto == null){
            throw new Exception("Produto: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        return produto;
    }

    public void deleteProduto(ProdutoDeleteRequest produtoDeleteRequest) throws Exception {
        Categoria categoria = categoriaRepository.findById(produtoDeleteRequest.categoria_id).get();
        if(categoria == null){
            throw new Exception("Categoria: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoDeleteRequest.categoria_id);
        }

        Produto produto = produtoRepository.findById(produtoDeleteRequest.produto_id).get();
        if (produto == null){
            throw new Exception("Produto: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoDeleteRequest.produto_id);
        }

        categoria.getProdutos().remove(produto);
        categoriaRepository.save(categoria);
        produtoRepository.delete(produto);
    }

    public Produto updateProduto(ProdutoRequest produtoRequest) throws Exception {
        Produto produto = produtoRepository.findById(produtoRequest.produto_id).get();
        if (produto == null){
            throw new Exception("Produto: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoRequest.produto_id);
        }

        Categoria oldCategoria = categoriaRepository.findById(produto.getCategoria().getId()).get();
        if(oldCategoria == null){
            throw new Exception("Categoria: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoRequest.categoria_id);
        }

        Categoria newCategoria = categoriaRepository.findById(produtoRequest.categoria_id).get();
        if(newCategoria == null){
            throw new Exception("Categoria: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + produtoRequest.categoria_id);
        }

        oldCategoria.getProdutos().remove(produto);

        produto.setCategoria(newCategoria);
        produto.setName(produtoRequest.name);
        produto.setPrice(produtoRequest.price);

        newCategoria.getProdutos().add(produto);

        produtoRepository.save(produto);
        categoriaRepository.save(oldCategoria);
        categoriaRepository.save(newCategoria);

        return produto;
    }
}
