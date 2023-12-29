package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;
import com.servidor.gestiondeventas.repository.products.StoreSupplierRepository;
import com.servidor.gestiondeventas.services.products.tools.ProductSearchResult;
import com.servidor.gestiondeventas.tools.EntityEditor;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.repository.products.StoreRepository;
import com.servidor.gestiondeventas.services.products.ProductService;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreSupplierRepository storeSupplierRepository;
    private final EntityEditor<Product> productEditor;
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final SupplierRepository supplierRepository;
    private final StoreRepository storeRepository;
    private final StoreServiceImpl storeServiceImpl;
    private final StoreSupplierServiceImpl storeSupplierServiceImpl;

    @Override
    public Page<ProductDTO> getProduct(Pageable pageable) {
        // Obtener una página de entidades desde el repositorio
        Page<Product> productPage = productRepository.findAll(pageable);

        // Convertir la página de entidades a una página de DTOs

        return productPage.map(ProductDTO::fromEntity);
    }

    @Override
    public Optional<Product> getProductById(Long idProduct) {
        return productRepository.findById(idProduct);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        Product newProduct = new Product();
        List<Product> products = new ArrayList<Product>();

        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setCost_price(product.getCost_price());
        newProduct.setSelling_price(product.getSelling_price());
        newProduct.setImage(product.getImage());
        Product productSave = productRepository.save(newProduct);

        for (Store store : product.getStores()) {
            Store newStore = new Store();
            newStore.setCompany(store.getCompany());
            newStore.setStock(store.getStock());
            newStore.setStock_max(store.getStock_max());
            newStore.setStock_min(store.getStock_min());
            newStore.setProduct(productSave);
            productSave.getStores().add(storeRepository.save(newStore));

        }

        for (StoreSupplier storeSupplier : product.getStoreSuppliers()) {
            StoreSupplier newStoreSupplier = new StoreSupplier();
            Optional<Supplier> supplier = supplierRepository.findById(storeSupplier.getSupplier().getId());
            products.add(productSave);
            newStoreSupplier.setIdInternal(storeSupplier.getIdInternal());
            newStoreSupplier.setIdSupplierOne(storeSupplier.getIdSupplierOne());
            newStoreSupplier.setIdSupplierTwo(storeSupplier.getIdSupplierTwo());
            newStoreSupplier.setProducts(products);
            newStoreSupplier.setSupplier(supplier.orElse(null));
            productSave.getStoreSuppliers().add(storeSupplierRepository.save(newStoreSupplier));
        }

        return productSave;
    }

    @Override
    public ProductDTO editProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        Product product2 = productOptional.get();
        if (productOptional.isPresent()) {

            product2.setTitle(product.getTitle());
            product2.setDescription(product.getDescription());
            product2.setImage(product.getImage());
            product2.setCost_price(product.getCost_price());
            product2.setCreation_date(new Date());
            product2.setSelling_price(product.getSelling_price());

        }
        product.getStores().stream()
                .map(Store::getId)
                .distinct()
                .forEach(storeId -> storeRepository.findById(storeId)
                        .ifPresent(storeServiceImpl::editStore));

// Actualización de proveedores de tiendas
        product.getStoreSuppliers().stream()
                .map(StoreSupplier::getId)
                .distinct()
                .forEach(storeSupplierId -> storeSupplierRepository.findById(storeSupplierId)
                        .ifPresent(storeSupplierServiceImpl::editStoreSupplier));


        return ProductDTO.fromEntity(productRepository.save(product2));
    }

    @Transactional
    @Override
    public boolean deleteProduct(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);

        if (product.isEmpty()) {
            return false;
        }

        productRepository.delete(product.get());

        return true;

    }

    @Override
    public ProductSearchResult getProductByName(String text, int page, int size) {
        GenericSearchService<Product> genericSearchService = new GenericSearchService<>(entityManager, Product.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text, new String[]{"description"}, page, size);
        List<Product> products = (List<Product>) searchResult.get("resultQuery");
        System.out.println("\n----------------"+products.toString()+"\n-------------------");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        return new ProductSearchResult(productDTOList, totalElements);
    }

    @Override
    public List<ProductDTO> importExcel(List<Product> products) {
        for (Product product : products) {
            createProduct(product);
        }
        return null;
    }

    @Override
    public boolean updatePrice(ProductEditExcelDto productPrice) {

        List<StoreSupplier> storeSuppliers = storeSupplierRepository.selectByIdSupplierOne(productPrice.getIdSupplierOne());
        for (StoreSupplier storeSupplier : storeSuppliers) {
            if (storeSupplier.getProducts().size() > 0) {
                Product product = storeSupplier.getProducts().get(0);
                product.setCost_price(productPrice.getCost_price());
                product.setSelling_price(productPrice.getSelling_price());
                productRepository.save(product);
                return true;
            }
        }

        return false;
    }
}
