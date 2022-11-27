package com.project.ecommerce.Services;

import com.project.ecommerce.DTO.Request.ItemPedidoRequest;
import com.project.ecommerce.DTO.Request.PedidoRequest;
import com.project.ecommerce.DTO.Request.UpdateStatusPedido;
import com.project.ecommerce.Utils.Constantes;
import com.project.ecommerce.Utils.Utils;
import com.project.ecommerce.models.Cliente;
import com.project.ecommerce.models.ItemPedido;
import com.project.ecommerce.models.Pedido;
import com.project.ecommerce.models.Produto;
import com.project.ecommerce.models.enums.EstadoPagamento;
import com.project.ecommerce.models.enums.Pagamento;
import com.project.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pedido> findAllPedidos() throws Exception {
        List<Pedido> lstPedidos = pedidoRepository.findAll();
        if(lstPedidos.isEmpty()){
            throw new Exception("Pedido: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO
            );
        }

        return lstPedidos;
    }

    public Pedido findPedidoById(int id) throws Exception {
        Pedido pedido = pedidoRepository.findById(id).get();
        if (pedido == null){
            throw new Exception("Pedido: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + id);
        }

        return pedido;
    }

    public List<Pedido> findPedidosByClienteId(int id) throws Exception {
        List<Pedido> lstPedidos = pedidoRepository.findByClienteId(id);
        if(lstPedidos.isEmpty()){
            throw new Exception("Pedido: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO
            );
        }

        return lstPedidos;
    }

    public Pedido createNewPedido(PedidoRequest pedidoRequest) throws Exception {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(pedidoRequest.cliente_id).get();
        if (cliente == null) {
            throw new Exception("Cliente: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + pedidoRequest.cliente_id);
        }
        pedido.setCliente(cliente);


        List<ItemPedidoRequest> lstItemPedidoRequest = pedidoRequest.lstItemPedidoRequest;
        for (ItemPedidoRequest itemPedidoRequest: lstItemPedidoRequest) {
            Produto produto = produtoRepository.findById(itemPedidoRequest.produto_id).get();
            if (produto == null){
                throw new Exception("Pedido: "
                        + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                        + itemPedidoRequest.produto_id);
            }
            ItemPedido itemPedido = new ItemPedido(
                    pedido, produto, itemPedidoRequest.desconto,
                    itemPedidoRequest.quantidade, itemPedidoRequest.preco
            );
            pedido.getItensPedidos().add(itemPedido);
            itemPedidoRepository.save(itemPedido);
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setDtVencimento(new Date(pedidoRequest.pagamentoRequest.dtVencimento));

        if(pedidoRequest.pagamentoRequest.numeroParcelas > 0){
            pagamento.setNumeroParcelas(pedidoRequest.pagamentoRequest.numeroParcelas);
            pagamento.setDtPagamento(new Date(pedidoRequest.pagamentoRequest.dtPagamento));
            pagamento.setEstadoPagamento(EstadoPagamento.PAGO);
        }
        else {
            pagamento.setEstadoPagamento(EstadoPagamento.PENDENTE);
        }
        pagamentoRepository.save(pagamento);

        pedido.setPagamento(pagamento);
        pedido.setTotal(
                Utils.subTotal(pedido)
        );
        pedidoRepository.save(pedido);
        return pedido;
    }

    public void updateStatusPedido(UpdateStatusPedido updateStatusPedido) throws Exception {
        Pedido pedido = pedidoRepository.findById(updateStatusPedido.pedido_id).get();
        if (pedido == null){
            throw new Exception("Pedido: "
                    + Constantes.NENHUM_REGISTRO_ENCONTRADO_POR_ID
                    + updateStatusPedido.pedido_id);
        }
        Pagamento pagamento = pedido.getPagamento();
        pagamento.setEstadoPagamento(EstadoPagamento.toEnum(updateStatusPedido.status));

        pagamentoRepository.save(pagamento);
    }
}
