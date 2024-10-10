package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.services.receipts.DetailProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DetailProductServiceImpl implements DetailProductService {
    private final ProductRepository productRepository;

    @Override
    public Details createDetailProduct(Details details) {
        List<DetailProduct> detailProducts = new ArrayList<>();
        for(DetailProduct detailProduct : details.getDetailProductList()){
         Product product = productRepository.getById(detailProduct.getProductId());
         detailProduct.setDetails(details);
         detailProduct.setInternalCode(product.getIdInternal());
         detailProduct.setDescription(product.getDescription());

         detailProduct.setPrice(product.getSelling_price());
         detailProduct.setCostPrice(product.getCost_price());
         detailProduct.setTotalPrice(product.getSelling_price()*detailProduct.getQuality());
         detailProduct.setTotalCostPrice(product.getCost_price()*detailProduct.getQuality());

         detailProducts.add(detailProduct);
         //Descuento de Stock
            product.getStores().get(0).setStock(product.getStores().get(0).getStock()-detailProduct.getQuality());

        }
        details.setDetailProductList(detailProducts);
        return details;
    }
}
