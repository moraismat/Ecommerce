package com.project.ecommerce.models.enums;

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
