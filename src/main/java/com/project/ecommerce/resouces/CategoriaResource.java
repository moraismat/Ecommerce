package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.CategoriaCreateRequest;
import com.project.ecommerce.Services.CategoriaService;
import com.project.ecommerce.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "")
    private ResponseEntity findAllCategories(){
        try {
            List<Categoria> lstCategoria = categoriaService.findAll();

            return ResponseEntity.ok(lstCategoria);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity findCategoriesById(@PathVariable int id){
        try {
            Categoria categoria = categoriaService.findById(id);

            return ResponseEntity.ok(categoria);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping(value = "")
    private ResponseEntity createCategoria(@RequestBody CategoriaCreateRequest categoriaCreateRequest){
        try {
            Categoria newCategoria = categoriaService.createNewCategoria(categoriaCreateRequest);

            return ResponseEntity.ok(newCategoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
