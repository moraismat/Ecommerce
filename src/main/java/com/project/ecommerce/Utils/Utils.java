package com.project.ecommerce.Utils;

import com.project.ecommerce.models.ItemPedido;
import com.project.ecommerce.models.Pedido;

import java.util.List;

public class  Utils {

    public static double subTotal(Pedido pedido) {
        double total = 0;

        List<ItemPedido> lstItensPedidos = pedido.getItensPedidos().stream().toList();
        for (ItemPedido itemPedido: lstItensPedidos) {
            total += (itemPedido.getQuantidade())
                    * (itemPedido.getPreco() * (1.00 - itemPedido.getDesconto()));
        }

        return total;
    }
}
