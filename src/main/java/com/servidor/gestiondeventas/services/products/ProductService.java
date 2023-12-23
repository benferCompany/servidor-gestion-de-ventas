package com.servidor.gestiondeventas.services.products;



import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public List<ProductDTO> getProduct();
    public Optional<Product> getProductById(Long idProduct);
    public Product createProduct(Product product);
    public ProductDTO editProduct(Product product);
    public boolean deleteProduct(Long idProduct);
    public List<ProductDTO> getProductByName(String text);
    public List<ProductDTO> importExcel(List<Product> products);
    public boolean updatePrice(ProductEditExcelDto productPrice);
}
