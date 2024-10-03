package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;
import com.servidor.gestiondeventas.entities.products.dto.ProductShopDTO;
import com.servidor.gestiondeventas.entities.products.images.ImagesProduct;
import com.servidor.gestiondeventas.repository.balance.CapitalRepository;
import com.servidor.gestiondeventas.repository.products.StoreSupplierRepository;
import com.servidor.gestiondeventas.services.balance.CapitalService;
import com.servidor.gestiondeventas.services.products.DescriptionProductService;
import com.servidor.gestiondeventas.services.products.impl.StoreServiceImpl;
import com.servidor.gestiondeventas.services.products.impl.StoreSupplierServiceImpl;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.EntityEditor;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.repository.products.StoreRepository;
import com.servidor.gestiondeventas.services.products.ProductService;
import javax.persistence.*;

import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
    private final CapitalService capitalService;
    private final ImagesProductServiceImpl imagesProductService;
    private final DescriptionProductService descriptionProductService;
    private final CategoriesServiceImpl categoriesService;

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
    public ProductDTO createProduct(Product product) {
        Product newProduct = new Product();

        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setCost_price(product.getCost_price());
        newProduct.setSelling_price(product.getSelling_price());
        newProduct.setImage(product.getImage());
        newProduct.setIdInternal(product.getIdInternal());
        Product productSave = productRepository.save(newProduct);
        if (product.getStores() != null) {
            for (Store store : product.getStores()) {
                Store newStore = new Store();
                newStore.setCompany(store.getCompany());
                newStore.setStock(store.getStock());
                newStore.setStock_max(store.getStock_max());
                newStore.setStock_min(store.getStock_min());
                newStore.setProduct(productSave);
                productSave.getStores().add(storeRepository.save(newStore));

            }
            Double total = product.getStores().get(0).getStock() * product.getCost_price();
            capitalService.capitalSumStock(total);
        }

        if (product.getStoreSuppliers() != null) {
            for (StoreSupplier storeSupplier : product.getStoreSuppliers()) {
                StoreSupplier newStoreSupplier = new StoreSupplier();
                Optional<Supplier> supplier = supplierRepository.findById(storeSupplier.getSupplier().getId());
                newStoreSupplier.setIdSupplierOne(storeSupplier.getIdSupplierOne());
                newStoreSupplier.setIdSupplierTwo(storeSupplier.getIdSupplierTwo());
                newStoreSupplier.setProduct(productSave);
                newStoreSupplier.setSupplier(supplier.orElse(null));
                productSave.getStoreSuppliers().add(storeSupplierRepository.save(newStoreSupplier));
            }

        }

        return ProductDTO.fromEntity(productSave);
    }

    @Transactional
    @Override
    public ProductDTO editProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            Product product2 = productOptional.get();

            product2.setTitle(product.getTitle());
            product2.setDescription(product.getDescription());
            product2.setImage(product.getImage());
            product2.setCost_price(product.getCost_price());
            product2.setCreation_date(new Date());
            product2.setSelling_price(product.getSelling_price());
            product2.setIdInternal(product.getIdInternal());

            if (product.getStores() != null) {
                for (Store store : product.getStores()) {
                    if (store.getId() > 0) {
                        storeServiceImpl.editStore(store);
                    } else {
                        product2.getStores().add(storeRepository.save(store));
                    }
                }
            }

            // Actualización de proveedores de tiendas

            if (product.getStoreSuppliers() != null) {
                for (StoreSupplier storeSupplier : product.getStoreSuppliers()) {

                    if (storeSupplier.getId() > 0) {
                        storeSupplierServiceImpl.editStoreSupplier(storeSupplier);
                    } else {
                        product2.getStoreSuppliers().add(storeSupplierRepository.save(storeSupplier));

                    }

                }
            }

            return ProductDTO.fromEntity(productRepository.save(product2));
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteProduct(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        ImagesProduct imagesProduct = imagesProductService.getImagesProduct(idProduct);
        DescriptionProductDTO descriptionProductDTO = descriptionProductService.getDescriptionByIdProduct(idProduct);
        if (product.isEmpty()) {
            return false;
        }
        if (imagesProduct != null) {
            imagesProductService.deleteImagesProduct(imagesProduct.getId());
        }
        if (descriptionProductDTO != null) {
            descriptionProductService.deleteDescriptionProduct(descriptionProductDTO.getId());
        }

        productRepository.delete(product.get());

        return true;

    }

    @Override
    public ItemSearchResult getProductByName(String text, int page, int size) {
        GenericSearchService<Product> genericSearchService = new GenericSearchService<>(entityManager, Product.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text,
                new String[] { "title", "idInternal" }, page, size);
        List<Product> products = (List<Product>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        return new ItemSearchResult<>(productDTOList, totalElements);
    }

    @Transactional
    @Override
    public List<ProductDTO> importExcel(List<Product> products) {
        for (Product product : products) {
            createProduct(product);
        }
        return null;
    }

    @Transactional
    @Override
    public boolean updatePrice(ProductEditExcelDto productPrice) {

        List<StoreSupplier> storeSuppliers = storeSupplierRepository
                .selectByIdSupplierOne(productPrice.getIdSupplierOne());
        for (StoreSupplier storeSupplier : storeSuppliers) {
            if (storeSupplier.getProduct() != null) {
                Product product = storeSupplier.getProduct();
                product.setCost_price(productPrice.getCost_price());
                product.setSelling_price(productPrice.getSelling_price());
                productRepository.save(product);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ProductDTO> exportExcel() {

        return productRepository.findAll().stream().map(
                ProductDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Long lastElement() {
        Product product = productRepository.findFirstByOrderByIdDesc();
        return product.getId();
    }

    @Override
    public Message<ProductDTO> createOrUpdate(Product product) {
        Product productResponse = productRepository.findFirstByIdInternal(product.getIdInternal());
        String message;
        String status;
        ProductDTO productReturn;
        if (productResponse != null) {
            product.setId(productResponse.getId());
            message = "Producto actualizado con éxito";
            status = "UPDATE";
            productReturn = editProduct(product);
        } else {
            message = "Producto creado con éxito";
            status = "CREATED";
            productReturn = createProduct(product);

        }

        return new Message<>(productReturn, message, status);

    }

    @Override
    public PageImpl<ProductShopDTO> getProductsByCategory(String category, String text, int page, int size) {
        GenericSearchService<Product> genericSearchService = new GenericSearchService<>(entityManager, Product.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text,
                new String[] { "title", "idInternal" }, 0, 100);
        List<Product> products = (List<Product>) searchResult.get("resultQuery");
        List<ProductShopDTO> productShopDTO = products.stream()
                .map(ProductShopDTO::fromEntity)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, size);
        List<ProductShopDTO> productFilter = new ArrayList<ProductShopDTO>();
        if (!category.equals("")) {

            productShopDTO.stream()
                    .filter(pr -> pr.getCategories().stream()
                            .anyMatch(ct -> ct.getName().equals(category))) // Filtrar productos que tengan la categoría
                                                                            // deseada
                    .forEach(productFilter::add); // Agregar el producto filtrado a la lista

        } else {
            productFilter = productShopDTO;
        }
        // Aquí aplicamos la paginación manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productFilter.size());
        List<ProductShopDTO> paginatedList = productFilter.subList(start, end);

        Long totalElements = (long) productFilter.size();
        return new PageImpl<>(paginatedList, pageable, totalElements);
    }

    @Override
    public ProductDTO deleteCategoryInProduct(Product product) {
        return ProductDTO.fromEntity(productRepository.save(product));
    }
}
