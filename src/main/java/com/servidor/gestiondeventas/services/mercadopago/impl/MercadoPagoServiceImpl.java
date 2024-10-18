package com.servidor.gestiondeventas.services.mercadopago.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceFreeMethodRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentTypeRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.mercadopago.MercadoPagoService;
import com.servidor.gestiondeventas.services.mercadopago.WebhookEventService;
import com.servidor.gestiondeventas.services.products.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MercadoPagoServiceImpl implements MercadoPagoService {
    private final ProductService productService;
    private final WebhookEventService webhookEventService;
    @Override
    public Preference createPayment(Details details) throws MPException, MPApiException {

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


        //Establecer fecha de vencimiento de pagos en efectivo
        OffsetDateTime expirationDate = OffsetDateTime.now().plusHours(24);

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
                                .success("https://benfer.shop/miscompras")
                                .failure("https://benfer.shop")
                                .pending("https://benfer.shop/miscompras")
                                .build())
                .notificationUrl(
                        "https://benfer.shop/api/mercadoPago/webhooks?email=" + details.getCustomer().getEmail())
                .items(items).autoReturn("approved")
                .dateOfExpiration(expirationDate).build();
        Preference clientPreference = client.create(preferenceRequest, requestOptions);
        return clientPreference;

    }

    @Override
    public Payment getPaymentById(Long id) throws MPException, MPApiException {
        PaymentClient client = new PaymentClient();

        return client.get(id);

    }

    @Override
    public List<Payment> getPayment(List<WebhookEvent> webhooks) {
        List<Payment> payments = webhooks.stream()
                .map(wh -> {
                    try {
                        return getPaymentById(wh.getData().getId());
                    } catch (MPException | MPApiException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        return payments;
    }

@Override
public Page<Payment> getPaymentsByEmail(String email, int page, int size) throws MPException, MPApiException {
    List<WebhookEvent> webhooks = webhookEventService.getWebhooksByEmail(email);
    
    // Convierte la lista en una sublista para aplicar paginación
    Pageable pageable = PageRequest.of(page, size);
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), webhooks.size());
    
    List<Payment> pagedWebhooks = getPayment(webhooks.subList(start, end));
    
    return new PageImpl<>(pagedWebhooks, pageable, webhooks.size());
}


}
