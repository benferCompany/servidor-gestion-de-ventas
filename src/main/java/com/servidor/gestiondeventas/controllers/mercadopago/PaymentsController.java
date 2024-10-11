package com.servidor.gestiondeventas.controllers.mercadopago;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servidor.gestiondeventas.entities.mercadopago.payments.PaymentOrder;
import com.servidor.gestiondeventas.entities.mercadopago.payments.dto.PaymentOrderDTO;
import com.servidor.gestiondeventas.services.mercadopago.PaymentOrderService;
import com.servidor.gestiondeventas.tools.Message;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/mercadopago/payments")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentsController {
    private final PaymentOrderService paymentOrderService;
    
    @GetMapping
    public ResponseEntity<List<PaymentOrderDTO>> getPaymentsOrders(){
        return new ResponseEntity<>(paymentOrderService.getPayments(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentOrderDTO> getPaymentOrderById(@PathVariable String id){
        return new ResponseEntity<>(paymentOrderService.findPaymentOrderById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentOrderDTO> createPayment(@RequestBody PaymentOrder paymentOrder){
        return new ResponseEntity<>(paymentOrderService.createPaymentOrder(paymentOrder),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentOrderDTO> updatePaymentOrder(@RequestBody PaymentOrder paymentOrder){
        
        return new ResponseEntity<>(paymentOrderService.updatePaymentOrder(paymentOrder),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message<PaymentOrderDTO>> deletePaymentOrder(@PathVariable String id){
        return new ResponseEntity<>(paymentOrderService.deletePaymentOrder(id),HttpStatus.OK);
    }

}
