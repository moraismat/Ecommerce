package com.project.ecommerce.resouces;

import com.project.ecommerce.DTO.Request.PedidoRequest;
import com.project.ecommerce.DTO.Request.UpdateStatusPedido;
import com.project.ecommerce.DTO.Response.ItemPedidoResponse;
import com.project.ecommerce.DTO.Response.PedidoResponse;
import com.project.ecommerce.Services.PedidoService;
import com.project.ecommerce.models.ItemPedido;
import com.project.ecommerce.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "")
    private ResponseEntity findAllPedidos(){
        try {
            List<Pedido> lstPedidos = pedidoService.findAllPedidos();



            return ResponseEntity.ok(lstPedidos);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity findPedidosById(@PathVariable int id){
        try {
            Pedido pedido = pedidoService.findPedidoById(id);

            PedidoResponse pedidoResponse = new PedidoResponse();
            pedidoResponse.pedido_id = pedido.getId();
            pedidoResponse.cliente = pedido.getCliente();
            pedidoResponse.pagamento_id = pedido.getPagamento().getId();
            pedidoResponse.total = pedido.getTotal();

            for (ItemPedido itemPedido: pedido.getItensPedidos()) {
                ItemPedidoResponse itemPedidoResponse = new ItemPedidoResponse();
                itemPedidoResponse.desconto = itemPedido.getDesconto();
                itemPedidoResponse.preco = itemPedido.getPreco();
                itemPedidoResponse.quantidade = itemPedido.getQuantidade();
                itemPedidoResponse.produto_id = itemPedido.getProduto().getId();

                pedidoResponse.itensPedidos.add(itemPedidoResponse);
            }

            return ResponseEntity.ok().body(pedidoResponse);
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping(value = "/create")
    private ResponseEntity createPedido(@RequestBody PedidoRequest pedidoRequest){
        try {
            Pedido pedido = pedidoService.createNewPedido(pedidoRequest);
            Object pedidoResponse = new Object(){

            };

            return ResponseEntity.ok().body(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}/update")
    private ResponseEntity updatePedido(@PathVariable int id, @RequestBody UpdateStatusPedido updateStatusPedido){
        try {
            pedidoService.updateStatusPedido(updateStatusPedido);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}