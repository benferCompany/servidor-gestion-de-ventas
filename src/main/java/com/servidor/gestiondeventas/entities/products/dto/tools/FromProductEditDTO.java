package com.servidor.gestiondeventas.entities.products.dto.tools;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditDTO;
import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor

public class FromProductEditDTO {
    final ProductRepository productRepository;
    final CompanyRepository companyRepository;

    public Product converProductEditDTO(ProductEditDTO productEditDTO) {
        Product product;
        if (productEditDTO.getId() != null) {
            Optional<Product> productOptional = productRepository.findById(productEditDTO.getId());

            product = productOptional.orElseGet(() -> new Product());
        } else {
            product = new Product();
            product.setCreation_date(new Date());
        }
        product.setDescription(productEditDTO.getDescription());
        product.setTitle(productEditDTO.getTitle());
        product.setSelling_price(productEditDTO.getSelling_price());
        product.setImage(productEditDTO.getImage());
        product.setCost_price(productEditDTO.getCost_price());

        if (productEditDTO.getStoreId() != null) {
            // Verifica si la lista de tiendas no es nula
            if (product.getStores() == null) {
                product.setStores(new ArrayList<>());
            }

            // Busca la tienda por su ID
            Store storeToUpdate = product.getStores().stream()
                    .filter(store -> Objects.equals(store.getId(), productEditDTO.getStoreId()))
                    .findFirst()
                    .orElse(null);

            if (storeToUpdate != null) {
                // Actualiza los campos de la tienda
                if (productEditDTO.getStock() != 0) {
                    storeToUpdate.setStock(productEditDTO.getStock());
                }
                if (productEditDTO.getStockMax() != 0) {
                    storeToUpdate.setStock_max(productEditDTO.getStockMax());
                }
                if (productEditDTO.getStockMin() != 0) {
                    storeToUpdate.setStock_min(productEditDTO.getStockMin());
                }

            } else {
                // Crea una nueva tienda si no se encontró la existente
                Store store = new Store();
                store.setCompany(companyRepository.findById(productEditDTO.getCompanyId()).orElse(null));
                store.setStock(productEditDTO.getStock());
                store.setStock_max(productEditDTO.getStockMax());
                store.setStock_min(productEditDTO.getStockMin());
                store.setProduct(product);
                store.setLast_modication(new Date());
                product.getStores().add(store);
            }
        }

        return productRepository.save(product);

    }
}
