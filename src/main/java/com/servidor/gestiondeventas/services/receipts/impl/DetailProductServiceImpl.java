package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.repository.receipts.DetailProductsRepository;
import com.servidor.gestiondeventas.services.receipts.DetailProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DetailProductServiceImpl implements DetailProductService {
    private final DetailProductsRepository detailProductsRepository;
    private final ProductRepository productRepository;
    @Override
    public Details createDetailProduct(Details details) {
        List<DetailProduct> detailsProducts = new ArrayList<>();
        for(DetailProduct detailProduct : details.getDetailProductList()){
            Product product = productRepository.getById(detailProduct.getProduct().getId());
            detailProduct.setDetails(details);
            detailProduct.setProduct(product);
            detailsProducts.add(detailProduct);
            detailProductsRepository.save(detailProduct);
        }
        details.setDetailProductList(detailsProducts);
        return details;
    }
}
