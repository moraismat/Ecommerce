package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.ProdutoRequest;
import com.project.ecommerce.DTO.Response.ProdutoResponse;
import com.project.ecommerce.Services.ProdutoService;
import com.project.ecommerce.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/create")
    public ResponseEntity createNewProduto(@RequestBody ProdutoRequest produtoRequest) {
        try {
            Produto produto = produtoService.addProduto(produtoRequest);
            ProdutoResponse produtoResponse = new ProdutoResponse();
            produtoResponse.name = produto.getName();
            produtoResponse.price = produto.getPrice();
            produtoResponse.categoria = produto.getCategoria().getName();

            return ResponseEntity.ok().body(produtoResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
