package com.servidor.gestiondeventas.controllers.mercadopago;

import com.mercadopago.client.payment.*;
import com.mercadopago.client.preference.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.mercadopago.WebhookEventService;
import com.servidor.gestiondeventas.services.products.ProductService;
import com.servidor.gestiondeventas.services.receipts.DetailsService;

import lombok.AllArgsConstructor;

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
    private final ProductService productService;
    private final WebhookEventService webhookEventService;
    private final DetailsService detailsService;

    @PostMapping
    public Preference getApi(@RequestBody Details details) throws MPException, MPApiException, IOException {
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", "0d5020ed-1af6-469c-ae06-c3bec19954bb");

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();
        PreferenceClient client = new PreferenceClient();
        List<PreferenceItemRequest> items = new ArrayList<>();
        for (DetailProduct detailProduct : details.getDetailProductList()) {
            Optional<Product> product = productService.getProductById(detailProduct.getProductId());
            if (product.isPresent()) {
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .id(detailProduct.getProductId().toString())
                        .title(detailProduct.getDescription())
                        .description(detailProduct.getDescription())
                        .pictureUrl(product.get().getImage())
                        .categoryId("categoria")
                        .quantity((int) Double.parseDouble(String.valueOf(detailProduct.getQuality())))
                        .currencyId("ARS")
                        .unitPrice(BigDecimal.valueOf(detailProduct.getPrice()))
                        .build();

                items.add(itemRequest);

            }

        }

        PreferenceFreeMethodRequest freeMethod = PreferenceFreeMethodRequest.builder()
                .id(1L).build();
        List<PreferenceFreeMethodRequest> freeMethodList = new ArrayList<>();
        freeMethodList.add(freeMethod);

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();
        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("").build());

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("https://benfer.shop")
                                .failure("https://benfer.shop/carrito")
                                .pending("https://benfer.shop")
                                .build())
                .notificationUrl("https://benfer.shop/api/mercadoPago/webhooks")
                .items(items).autoReturn("approved").build();
        Preference clientPreference = client.create(preferenceRequest, requestOptions);
        return clientPreference;

    }

    @GetMapping("/pago/{id}")
    public Payment getPago(@PathVariable Long id) throws MPException, MPApiException {
        PaymentClient client = new PaymentClient();

        return client.get(id);

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
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookEvent payload) {
        // Aquí puedes procesar el payload
        System.out.println("Notificación recibida: " + payload);
        webhookEventService.createWebhookEvent(payload);
        return ResponseEntity.ok("Webhook recibido correctamente");
    }

    @GetMapping("/webhooks")
    public ResponseEntity<List<WebhookEvent>> getWebHooks() {
        return new ResponseEntity<>(webhookEventService.getWebHooks(), HttpStatus.OK);
    }
}
