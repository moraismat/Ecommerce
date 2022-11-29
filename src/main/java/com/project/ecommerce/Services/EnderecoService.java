package com.project.ecommerce.Services;

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
public class EnderecoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() throws Exception {
        List<Endereco> lstEndereco = enderecoRepository.findAll();
        if(lstEndereco.isEmpty()){
            throw new Exception("Enderco: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO
            );
        }

        return lstEndereco;
    }

    public Endereco findById(int id) throws Exception {
        Endereco endereco = enderecoRepository.findById(id).get();
        if (endereco == null){
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        return endereco;
    }

    public Endereco addEndereco(EnderecoRequest enderecoCreateRequest) throws Exception {
        Cliente cliente = clienteRepository.findById(enderecoCreateRequest.cliente_id).get();
        if(cliente == null){
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + enderecoCreateRequest.cliente_id);
        }

        if(cliente.getEndereco() != null){
            throw new Exception("Endereco: "
                    + Constantes.ENDERECO_CADASTRADO_CLIENTE
                    + enderecoCreateRequest.cliente_id);

        }

        Endereco endereco = new Endereco(enderecoCreateRequest.logradouro, enderecoCreateRequest.numero,
                enderecoCreateRequest.complemento, enderecoCreateRequest.bairro, enderecoCreateRequest.cep,
                enderecoCreateRequest.cidade, enderecoCreateRequest.estado);
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);

        enderecoRepository.save(endereco);
        clienteRepository.save(cliente);

        return endereco;
    }

    public void deleteEndereco(Integer id) throws Exception {
        Endereco endereco = enderecoRepository.findById(id).get();
        if(endereco == null){
            throw new Exception("Endereco: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        enderecoRepository.delete(endereco);
    }

    public Endereco updateEndereco(EnderecoRequest enderecoCreateRequest) throws Exception {
        Endereco endereco = enderecoRepository.findById(enderecoCreateRequest.endereco_id).get();
        if(endereco == null){
            throw new Exception("Endereco: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + enderecoCreateRequest.endereco_id);
        }
        endereco.setBairro(enderecoCreateRequest.bairro);
        endereco.setCep(enderecoCreateRequest.cep);
        endereco.setComplemento(enderecoCreateRequest.complemento);
        endereco.setLogradouro(enderecoCreateRequest.logradouro);
        enderecoRepository.save(endereco);

        return endereco;
    }
}
