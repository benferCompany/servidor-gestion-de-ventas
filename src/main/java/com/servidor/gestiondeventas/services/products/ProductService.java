package com.servidor.gestiondeventas.services.products;


import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;
import com.servidor.gestiondeventas.entities.products.dto.ProductShopDTO;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public Page<ProductDTO> getProduct(Pageable pageable);

    public Optional<Product> getProductById(Long idProduct);

    public ProductDTO createProduct(Product product);

    public ProductDTO editProduct(Product product);

    public boolean deleteProduct(Long idProduct);

    public ItemSearchResult getProductByName(String text, int page, int size);

    public List<ProductDTO> importExcel(List<Product> products);

    public boolean updatePrice(ProductEditExcelDto productPrice);

    public List<ProductDTO> exportExcel();
    public Long lastElement();

    public Message<ProductDTO> createOrUpdate(Product product);
    public List<ProductShopDTO> getProductsByCategory(String category);
    public ProductDTO deleteCategoryInProduct(Product product);
}
