package com.servidor.gestiondeventas.services.mercadopago.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.servidor.gestiondeventas.services.products.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MercadoPagoServiceImpl implements MercadoPagoService {
    private final ProductService productService;

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
                .notificationUrl(
                        "https://benfer.shop/api/mercadoPago/webhooks?email=" + details.getCustomer().getEmail())
                .items(items).autoReturn("approved").build();
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

}
