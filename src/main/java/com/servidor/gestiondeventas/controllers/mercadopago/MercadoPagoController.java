package com.servidor.gestiondeventas.controllers.mercadopago;


import com.mercadopago.client.payment.*;
import com.mercadopago.client.preference.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.products.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/mercadoPago")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MercadoPagoController {
    private final ProductService productService;

    @PostMapping
    public Preference getApi(@RequestBody Details details) throws MPException, MPApiException {
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", "0d5020ed-1af6-469c-ae06-c3bec19954bb");

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();
        PreferenceClient client = new PreferenceClient();
        List<PreferenceItemRequest> items = new ArrayList<>();
        for(DetailProduct detailProduct : details.getDetailProductList())
        {
            Optional<Product> product = productService.getProductById(detailProduct.getProductId());
            if(product.isPresent()){
                PreferenceItemRequest itemRequest =
                        PreferenceItemRequest.builder()
                                .id(detailProduct.getProductId().toString())
                                .title(detailProduct.getDescription())
                                .description(detailProduct.getDescription())
                                .pictureUrl("https://www.wurth.com.ar/blog/wp-content/uploads/2022/11/martillo-de-carpintero.jpg")
                                .categoryId(product.get().getCategories().toString())
                                .quantity((int) Double.parseDouble("5.0"))
                                .currencyId("ARS")
                                .unitPrice(BigDecimal.valueOf(detailProduct.getPrice()))
                                .build();


                items.add(itemRequest);

            }

        }

        PreferenceFreeMethodRequest freeMethod =
                PreferenceFreeMethodRequest.builder()
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
                                .success("http://localhost:3001/statePage")
                                .failure("http://localhost:3001/statePage")
                                .pending("http://localhost:3001/statePage")
                                .build())
                .items(items).autoReturn("approved").build();

        return client.create(preferenceRequest,requestOptions);

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
        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
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

}
