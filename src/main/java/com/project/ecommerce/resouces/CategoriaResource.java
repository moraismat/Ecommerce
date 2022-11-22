package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.CategoriaRequest;
import com.project.ecommerce.Services.CategoriaService;
import com.project.ecommerce.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/create")
    private ResponseEntity createCategoria(@RequestBody CategoriaRequest categoriaCreateRequest){
        try {
            Categoria newCategoria = categoriaService.createNewCategoria(categoriaCreateRequest);

            return ResponseEntity.ok(newCategoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    private ResponseEntity deleteCategoria(@PathVariable int id){
        try {
            categoriaService.deleteCategoria(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}/update")
    private ResponseEntity updateCategoria(@PathVariable int id, @RequestBody CategoriaRequest categoriaRequest){
        try {
            Categoria categoria = categoriaService.updateCategoria(id, categoriaRequest);

            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
