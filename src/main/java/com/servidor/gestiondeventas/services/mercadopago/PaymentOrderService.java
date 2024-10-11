package com.servidor.gestiondeventas.services.mercadopago;

import java.util.List;

import com.servidor.gestiondeventas.entities.mercadopago.payments.PaymentOrder;
import com.servidor.gestiondeventas.entities.mercadopago.payments.dto.PaymentOrderDTO;
import com.servidor.gestiondeventas.tools.Message;

public interface PaymentOrderService {
 public List<PaymentOrderDTO> getPayments();
 public PaymentOrderDTO findPaymentOrderById(String id);
 public PaymentOrderDTO createPaymentOrder(PaymentOrder paymentOrder);
 public PaymentOrderDTO updatePaymentOrder(PaymentOrder paymentOrder);
 public Message<PaymentOrderDTO> deletePaymentOrder(String id);   
}
