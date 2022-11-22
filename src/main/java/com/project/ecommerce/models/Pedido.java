package com.project.ecommerce.models;

import com.project.ecommerce.models.enums.Pagamento;

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

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    private double total;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedidos = new HashSet<ItemPedido>();;

    public Pedido() {}

    public Pedido(Pagamento pagamento, double total) {
        this.pagamento = pagamento;
        this.total = total;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
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
