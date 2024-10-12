package com.servidor.gestiondeventas.controllers.mercadopago;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentItem;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.entities.mercadopago.payments.Items;
import com.servidor.gestiondeventas.entities.mercadopago.payments.PaymentOrder;
import com.servidor.gestiondeventas.entities.mercadopago.payments.dto.PaymentOrderDTO;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.mercadopago.MercadoPagoService;
import com.servidor.gestiondeventas.services.mercadopago.WebhookEventService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mercadoPago")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MercadoPagoController {
    private final MercadoPagoService mercadoPagoService;
    private final WebhookEventService webhookEventService;

    @GetMapping("getPayments/{email}")
    public ResponseEntity<List<PaymentOrderDTO>> getPaymentsByEmail(@PathVariable String email)
            throws MPException, MPApiException {
        List<Payment> response = mercadoPagoService.getPayment(webhookEventService.getWebhooksByEmail(email));
        List<PaymentOrder> paymentOrders = new ArrayList<>();
        paymentOrders = response.stream().map(rs->{
            PaymentOrder paymentOrder = new PaymentOrder();
            List<PaymentItem> paymentItems = rs.getAdditionalInfo().getItems();
            List<Items> items = new ArrayList<>();

            paymentOrder.setId(rs.getId().toString());
            paymentOrder.setStatus(rs.getStatus());
            paymentOrder.setStatusDetail(rs.getStatusDetail());
            items =paymentItems.stream().map(pmitm ->{
                Items item = new Items();
                item.setCategoryId(pmitm.getCategoryId());
                item.setDescription(pmitm.getDescription());
                item.setId(Long.parseLong(pmitm.getId()));
                item.setPictureUrl(pmitm.getPictureUrl());
                item.setQuantity(pmitm.getQuantity());
                item.setUnitPrice(Long.parseLong(pmitm.getUnitPrice().toString()));
                return item;
            }).collect(Collectors.toList());
            paymentOrder.setItems(items);
           return paymentOrder;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(paymentOrders.stream().map(PaymentOrderDTO::fromEntity).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public Preference getApi(@RequestBody Details details) throws MPException, MPApiException, IOException {
        return mercadoPagoService.createPayment(details);

    }

    @GetMapping("/pago/{id}")
    public Payment getPago(@PathVariable Long id) throws MPException, MPApiException {

        return mercadoPagoService.getPaymentById(id);
    }

    @GetMapping("/token")
    public Preference postRequest() throws MPException, MPApiException {
        PreferenceClient client = new PreferenceClient();

        // Create an item in the preference
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("My product")
                .quantity(1)
                .unitPrice(new BigDecimal("100"))
                .build();
        items.add(item);

        PreferenceRequest request = PreferenceRequest.builder()
                // o .purpose('wallet_purchase') only allows logged payments
                // to allow guest payments you can omit this line
                .purpose("wallet_purchase")
                .items(items).build();

        return client.create(request);

    }

    @PostMapping("/webhooks")
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookEvent payload, @RequestParam String email) {
        // Aquí puedes procesar el payload
        payload.setEmail(email);
        System.out.println("Notificación recibida: " + payload);
        webhookEventService.createWebhookEvent(payload);
        return ResponseEntity.ok("Webhook recibido correctamente");
    }

    @GetMapping("/webhooks")
    public ResponseEntity<List<WebhookEvent>> getWebHooks() {
        return new ResponseEntity<>(webhookEventService.getWebHooks(), HttpStatus.OK);
    }
}
