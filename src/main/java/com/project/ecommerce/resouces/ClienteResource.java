package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.CategoriaRequest;
import com.project.ecommerce.DTO.Request.ClienteCreateRequest;
import com.project.ecommerce.Services.CategoriaService;
import com.project.ecommerce.Services.ClienteService;
import com.project.ecommerce.models.Categoria;
import com.project.ecommerce.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "")
    private ResponseEntity getAllClientes(){
        try {
            List<Cliente> lstCliente = clienteService.findAll();

            return ResponseEntity.ok(lstCliente);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity getClienteById(@PathVariable int id){
        try {
            Cliente cliente = clienteService.findById(id);

            return ResponseEntity.ok(cliente);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping(value = "/create")
    private ResponseEntity createNewCliente(@RequestBody ClienteCreateRequest clienteCreateRequest){
        try {
            Cliente cliente = clienteService.createCliente(clienteCreateRequest);

            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    private ResponseEntity deleteCliente(@PathVariable int id){
        try {
            clienteService.deleteCliente(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}/update")
    private ResponseEntity updateCliente(@PathVariable int id, @RequestBody ClienteCreateRequest clienteCreateRequest){
        try {
            Cliente cliente = clienteService.updateCliente(clienteCreateRequest);

            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
