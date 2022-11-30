package com.project.ecommerce.DTO.Response;

import com.project.ecommerce.models.Cliente;
import com.project.ecommerce.models.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PedidoResponse {
    public Integer pedido_id;
    public Integer pagamento_id;
    public double total;
    public Cliente cliente;
    public List<ItemPedidoResponse> itensPedidos = new ArrayList<>();

    public PedidoResponse() {}
}
