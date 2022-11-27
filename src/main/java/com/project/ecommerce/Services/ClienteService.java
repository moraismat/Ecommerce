package com.project.ecommerce.Services;


import com.project.ecommerce.DTO.Request.ClienteRequest;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.models.Cliente;
import com.project.ecommerce.repositories.ClienteRepository;
import com.project.ecommerce.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() throws Exception {
        List<Cliente> lstClientes = clienteRepository.findAll();
        if(lstClientes.isEmpty()){
            throw new Exception("Cliente: "+Constantes.NENHUM_REGISTRO_ENCONTRADO);
        }

        return lstClientes;
    }

    public Cliente findById(int id) throws Exception {
        Cliente cliente = clienteRepository.findById(id).get();
        if (cliente == null){
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        return cliente;
    }

    public Cliente createCliente(ClienteRequest clienteCreateRequest) throws Exception {
        Cliente cliente = clienteRepository.findByCPF(clienteCreateRequest.cpf);
        if(cliente != null){
            throw new Exception("Cliente: "
                    + Constantes.CPF_JA_CADASTRADO
                    + clienteCreateRequest.cpf);
        }

        cliente = clienteRepository.findByEMAIL(clienteCreateRequest.email);
        if(cliente != null){
            throw new Exception("Cliente: "
                    + Constantes.EMAIL_JA_CADASTRADO
                    + clienteCreateRequest.email);
        }

        cliente.setName(clienteCreateRequest.name);
        cliente.setCPF(clienteCreateRequest.cpf);
        cliente.setEMAIL(clienteCreateRequest.email);
        cliente.setTelefone(clienteCreateRequest.telefone);

        clienteRepository.save(cliente);
        return  cliente;
    }

    public Cliente updateCliente(ClienteRequest clienteCreateRequest) throws Exception {
        Cliente cliente = clienteRepository.findById(clienteCreateRequest.id).get();
        if(cliente == null){
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + clienteCreateRequest.id);
        }

        cliente.setName(clienteCreateRequest.name);
        cliente.setCPF(clienteCreateRequest.cpf);
        cliente.setEMAIL(clienteCreateRequest.email);
        cliente.setTelefone(clienteCreateRequest.telefone);

        clienteRepository.save(cliente);
        return  cliente;
    }

    public void deleteCliente(int id) throws Exception {
        Cliente cliente = clienteRepository.findById(id).get();
        if(cliente == null){
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        clienteRepository.delete(cliente);
    }
}
