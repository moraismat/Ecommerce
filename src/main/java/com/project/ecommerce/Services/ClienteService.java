package com.project.ecommerce.Services;


import com.project.ecommerce.DTO.Request.ClienteRequest;
import com.project.ecommerce.DTO.Request.EnderecoRequest;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.models.Cliente;
import com.project.ecommerce.models.Endereco;
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

    public Cliente createCliente(ClienteRequest clienteRequest) throws Exception {
        Cliente cliente = clienteRepository.findByCPF(clienteRequest.cpf);
        if(cliente != null){
            throw new Exception("Cliente: "
                    + Constantes.CPF_JA_CADASTRADO
                    + clienteRequest.cpf);
        }

        cliente = clienteRepository.findByEMAIL(clienteRequest.email);
        if(cliente != null){
            throw new Exception("Cliente: "
                    + Constantes.EMAIL_JA_CADASTRADO
                    + clienteRequest.email);
        }

        cliente = new Cliente();
        cliente.setName(clienteRequest.name);
        cliente.setCPF(clienteRequest.cpf);
        cliente.setEMAIL(clienteRequest.email);
        cliente.setTelefone(clienteRequest.telefone);

        EnderecoRequest enderecoRequest = clienteRequest.enderecoRequest;

        Endereco endereco = new Endereco(enderecoRequest.logradouro, enderecoRequest.numero,
                enderecoRequest.complemento, enderecoRequest.bairro, enderecoRequest.cep,
                enderecoRequest.cidade, enderecoRequest.estado);

        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);

        enderecoRepository.save(endereco);
        clienteRepository.save(cliente);

        clienteRepository.save(cliente);
        return  cliente;
    }
}
