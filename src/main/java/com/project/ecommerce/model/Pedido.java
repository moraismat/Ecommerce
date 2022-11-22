package com.project.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pagamento;
    private double total;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedidos = new HashSet<ItemPedido>();;

    public Pedido() {}

    public Pedido(String pagamento, double total) {
        this.pagamento = pagamento;
        this.total = total;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(Set<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
