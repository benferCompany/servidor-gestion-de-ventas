package com.servidor.gestiondeventas.services.mercadopago.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.servidor.gestiondeventas.entities.mercadopago.payments.PaymentOrder;
import com.servidor.gestiondeventas.entities.mercadopago.payments.dto.PaymentOrderDTO;
import com.servidor.gestiondeventas.repository.mercadopago.PaymentOrderRepository;
import com.servidor.gestiondeventas.services.mercadopago.PaymentOrderService;
import com.servidor.gestiondeventas.tools.Message;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService {
    private final PaymentOrderRepository paymentOrderRepository;

    @Override
    public List<PaymentOrderDTO> getPayments() {

        return paymentOrderRepository.findAll().stream().map(PaymentOrderDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public PaymentOrderDTO findPaymentOrderById(String id) {
        return PaymentOrderDTO.fromEntity(paymentOrderRepository.findById(id).get());
    }

    @Override
    public PaymentOrderDTO createPaymentOrder(PaymentOrder paymentOrder) {
        paymentOrder.setItems(paymentOrder.getItems().stream()
                .map(p -> {
                    p.setPaymentOrder(paymentOrder); // Establece la relación
                    return p; // Retorna el objeto modificado
                })
                .collect(Collectors.toList()));
        
        return PaymentOrderDTO.fromEntity(paymentOrderRepository.save(paymentOrder));
    }

    @Override
    public PaymentOrderDTO updatePaymentOrder(PaymentOrder paymentOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePaymentOrder'");
    }

    @Override
    public Message<PaymentOrderDTO> deletePaymentOrder(String id) {
        Message<PaymentOrderDTO> message = new Message<>();
        Optional<PaymentOrder> paymentOrder = paymentOrderRepository.findById(id);
        if (paymentOrder.isPresent()) {
            paymentOrderRepository.deleteById(id);
            message.setEntity(PaymentOrderDTO.fromEntity(paymentOrder.get()));
            message.setMessage("La orden de pago se elimino correctamente");
            message.setStatus("true");
            return message;
        }
        message.setMessage("No se encontro la orden de pago");
        message.setStatus("false");
        return message;

    }
}
