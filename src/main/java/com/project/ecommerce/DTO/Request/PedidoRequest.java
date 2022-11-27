package com.project.ecommerce.DTO.Request;

import java.util.List;

public class PedidoRequest {
    public double total;
    public Integer cliente_id;
    public PagamentoRequest pagamentoRequest;
    public List<ItemPedidoRequest> lstItemPedidoRequest;

    public PedidoRequest() {}
}
