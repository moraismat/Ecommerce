package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.ProdutoDeleteRequest;
import com.project.ecommerce.DTO.Request.ProdutoRequest;
import com.project.ecommerce.DTO.Response.ProdutoResponse;
import com.project.ecommerce.Services.ProdutoService;
import com.project.ecommerce.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "")
    public ResponseEntity getAllProdutos(){
        try {
            List<Produto> lstProdutos = produtoService.findAll();

            return ResponseEntity.ok().body(lstProdutos);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAllProdutos(@PathVariable int id){
        try {
            Produto produto = produtoService.findById(id);
            ProdutoResponse produtoResponse = new ProdutoResponse();
            produtoResponse.categoria_id = produto.getId();
            produtoResponse.name = produto.getName();
            produtoResponse.price = produto.getPrice();
            produtoResponse.categoria = produto.getCategoria().getName();
            produtoResponse.categoria_id = produto.getCategoria().getId();

            return ResponseEntity.ok().body(produto);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity createNewProduto(@RequestBody ProdutoRequest produtoRequest) {
        try {
            Produto produto = produtoService.addProduto(produtoRequest);
            ProdutoResponse produtoResponse = new ProdutoResponse();
            produtoResponse.categoria_id = produto.getId();
            produtoResponse.name = produto.getName();
            produtoResponse.price = produto.getPrice();
            produtoResponse.categoria = produto.getCategoria().getName();
            produtoResponse.categoria_id = produto.getCategoria().getId();

            return ResponseEntity.ok().body(produtoResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity updateProduto(@RequestBody ProdutoRequest produtoRequest) {
        try {
            Produto produto = produtoService.updateProduto(produtoRequest);
            ProdutoResponse produtoResponse = new ProdutoResponse();
            produtoResponse.categoria_id = produto.getId();
            produtoResponse.name = produto.getName();
            produtoResponse.price = produto.getPrice();
            produtoResponse.categoria = produto.getCategoria().getName();
            produtoResponse.categoria_id = produto.getCategoria().getId();

            return ResponseEntity.ok().body(produtoResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteProduto(@RequestBody ProdutoDeleteRequest produtoDeleteRequest) {
        try {
            produtoService.deleteProduto(produtoDeleteRequest);

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
