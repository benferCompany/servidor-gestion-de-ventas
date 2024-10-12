package com.servidor.gestiondeventas.controllers.mercadopago;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.mercadopago.MercadoPagoService;
import com.servidor.gestiondeventas.services.mercadopago.WebhookEventService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/mercadoPago")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MercadoPagoController {
    private final MercadoPagoService mercadoPagoService;
    private final WebhookEventService webhookEventService;

 @GetMapping("getPayments/{email}")
public ResponseEntity<Page<Payment>> getPaymentsByEmail(
        @PathVariable String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) throws MPException, MPApiException {
    
    Page<Payment> response = mercadoPagoService.getPaymentsByEmail(email, page, size);
    return new ResponseEntity<>(response, HttpStatus.OK);
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
