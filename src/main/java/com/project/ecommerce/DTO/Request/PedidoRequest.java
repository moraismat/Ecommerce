package com.project.ecommerce.DTO.Request;

import java.util.List;

public class PedidoRequest {
    public Integer cliente_id;
    public double total;
    public PagamentoRequest pagamentoRequest;
    public List<ItemPedidoRequest> lstItemPedidoRequest;

    public PedidoRequest() {}
}
