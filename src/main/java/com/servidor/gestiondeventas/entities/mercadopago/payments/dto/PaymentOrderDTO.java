package com.servidor.gestiondeventas.entities.mercadopago.payments.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.servidor.gestiondeventas.entities.mercadopago.payments.PaymentOrder;

import lombok.Data;

@Data
public class PaymentOrderDTO {
    private String id;
    private List<ItemDTO> items;

    public static PaymentOrderDTO fromEntity(PaymentOrder paymentOrder) {
        PaymentOrderDTO paymentOrderDTO = new PaymentOrderDTO();
        paymentOrderDTO.setId(paymentOrder.getId());
        paymentOrderDTO.setItems(paymentOrder.getItems().stream().map(ItemDTO::fromEntity).collect(Collectors.toList()));
        return paymentOrderDTO;
    }
}
