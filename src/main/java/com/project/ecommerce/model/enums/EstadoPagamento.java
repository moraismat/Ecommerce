package com.project.ecommerce.model.enums;

public enum EstadoPagamento {
    PENDENTE("PENDENTE"),
    PAGO("PAGO");

    private String descricao;

    EstadoPagamento(String descricao){
        this.descricao = descricao;
    }

    private String getDescricao(){
        return this.descricao;
    }
}
