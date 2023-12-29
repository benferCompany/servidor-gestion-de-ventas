package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;
import com.servidor.gestiondeventas.entities.products.dto.tools.FromProductEditDTO;
import com.servidor.gestiondeventas.repository.products.StoreSupplierRepository;
import com.servidor.gestiondeventas.services.products.ProductService;
import com.servidor.gestiondeventas.services.products.StoreService;
import com.servidor.gestiondeventas.services.products.StoreSupplierService;

import javax.persistence.EntityManager;

import com.servidor.gestiondeventas.services.products.tools.ProductSearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Data
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private FromProductEditDTO fromProductEditDTO;

    final private StoreSupplierService storeSupplierService;
    final private StoreService storeService;
    final private StoreSupplierRepository storeSupplierRepository;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<ProductDTO> productPage = productService.getProduct(pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDTO> getSupplierById(@PathVariable Long idProduct) {
        return new ResponseEntity<>(ProductDTO.fromEntity(productService.getProductById(idProduct).get()), HttpStatus.OK);
    }

    @GetMapping("/productoColumn")
    public ResponseEntity<List<String>> getProductColumns() {
        Class<ProductEditExcelDto> dtoClass = ProductEditExcelDto.class;
        Field[] fields = dtoClass.getDeclaredFields();
        List<String> columnNames = new ArrayList<>();

        for (Field field : fields) {
            columnNames.add(field.getName());
        }


        return ResponseEntity.ok(columnNames);

    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {

        return new ResponseEntity<>(ProductDTO.fromEntity(productService.createProduct(product)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> editProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.editProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/{idProduct}")
    public String deleteProduct(@PathVariable Long idProduct) {
        boolean booleanProduct = productService.deleteProduct(idProduct);
        if (booleanProduct) {
            return "Se elimino el producto con éxito";
        }
        return "El id de este producto no existe";
    }

    @PostMapping("/name")
    public ResponseEntity<Page<ProductDTO>> getProductByName(
            @RequestBody Product product,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ProductSearchResult searchResult = productService.getProductByName(product.getDescription(), page, size);

        List<ProductDTO> productDTOList = searchResult.getProductDTOList();
        Long totalElements = searchResult.getTotalElements();

        return new ResponseEntity<>(new PageImpl<>(productDTOList, PageRequest.of(page, size), totalElements), HttpStatus.OK);
    }

    @PostMapping("/importExcel")
    public ResponseEntity<List<ProductDTO>> importExcel(@RequestBody List<Product> products) {
        List<ProductDTO> createdOrUpdatedProducts = productService.importExcel(products);
        return new ResponseEntity<>(createdOrUpdatedProducts, HttpStatus.CREATED);
    }

    @PostMapping("/updatePrice")
    public ResponseEntity<Boolean> updatePrice(@RequestBody ProductEditExcelDto productPrice) {

        return new ResponseEntity<>(productService.updatePrice(productPrice), HttpStatus.OK);
    }
}
