package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.EnderecoRequest;
import com.project.ecommerce.Services.EnderecoService;
import com.project.ecommerce.models.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value = "")
    private ResponseEntity getAllClientes(){
        try {
            List<Endereco> lstEndereco = enderecoService.findAll();

            return ResponseEntity.ok(lstEndereco);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity getEnderecoById(@PathVariable int id){
        try {
            Endereco endereco = enderecoService.findById(id);

            return ResponseEntity.ok(endereco);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping(value = "/create")
    private ResponseEntity createNewEndereco(@RequestBody EnderecoRequest enderecoCreateRequest){
        try {
            Endereco endereco = enderecoService.addEndereco(enderecoCreateRequest);

            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    private ResponseEntity deleteEndereco(@PathVariable int id){
        try {
            enderecoService.deleteEndereco(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    private ResponseEntity updateEndereco(@RequestBody EnderecoRequest enderecoCreateRequest){
        try {
            Endereco endereco = enderecoService.updateEndereco(enderecoCreateRequest);

            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
