package com.project.ecommerce.DTO.Request;

import com.project.ecommerce.models.enums.EstadoPagamento;

import java.util.Date;

public class PagamentoRequest {
    public Integer pedido_id;
    public String estadoPagamento;
    public String dtPagamento;
    public String dtVencimento;
    public int numeroParcelas;

    public PagamentoRequest(){}
}
