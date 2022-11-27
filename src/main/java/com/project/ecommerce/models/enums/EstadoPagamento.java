package com.project.ecommerce.models.enums;

public enum EstadoPagamento {
    PENDENTE("PENDENTE"),
    PAGO("PAGO"),
    CANCELADO("CANCELADO");

    private String descricao;

    EstadoPagamento(String descricao){
        this.descricao = descricao;
    }

    private String getDescricao(){
        return this.descricao;
    }

    public static EstadoPagamento toEnum(String statusPedido){
        if(statusPedido == null){
            return null;
        }
        for(EstadoPagamento x: EstadoPagamento.values()){
            if(statusPedido.equals(x.getDescricao())){
                return x;
            }

        }
        throw new IllegalArgumentException("Id Invalido: "+statusPedido);
    }

}
