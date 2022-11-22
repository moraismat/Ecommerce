package com.project.ecommerce.models.enums;

import com.project.ecommerce.models.Pedido;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private EstadoPagamento estadoPagamento;
    private Date dtPagamento;
    private Date dtVencimento;
    private int numeroParcelas;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Pagamento() {}

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Date dtPagamento, Date dtVencimento, int numeroParcelas, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = estadoPagamento;
        this.dtPagamento = dtPagamento;
        this.dtVencimento = dtVencimento;
        this.numeroParcelas = numeroParcelas;
        this.pedido = pedido;
    }

    public EstadoPagamento getEstadoPagamento() {
        return estadoPagamento;
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
