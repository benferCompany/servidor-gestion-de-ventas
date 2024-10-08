package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.ProductEditExcelDto;
import com.servidor.gestiondeventas.repository.products.StoreSupplierRepository;
import com.servidor.gestiondeventas.services.products.ProductService;
import com.servidor.gestiondeventas.services.products.StoreService;
import com.servidor.gestiondeventas.services.products.StoreSupplierService;

import javax.persistence.EntityManager;

import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Data
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private EntityManager entityManager;
    @Autowired

    final private StoreSupplierService storeSupplierService;
    final private StoreService storeService;
    final private StoreSupplierRepository storeSupplierRepository;


    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductDTO> getSupplierById(@PathVariable Long idProduct) {
        if(productService.getProductById(idProduct).isPresent()){
            return new ResponseEntity<>(ProductDTO.fromEntity(productService.getProductById(idProduct).get()), HttpStatus.OK);
        }
        return null;
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

        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
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

        @SuppressWarnings("rawtypes")
        ItemSearchResult itemSearchResult = productService.getProductByName(product.getDescription(), page, size);

        @SuppressWarnings("unchecked")
        List<ProductDTO> productDTOList = itemSearchResult.getResultList();
        Long totalElements = itemSearchResult.getTotalElements();

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

    @GetMapping("exportExcel")
    public ResponseEntity<List<ProductDTO>> exportExcel(){
        return new ResponseEntity<>(productService.exportExcel(),HttpStatus.OK);
    }
    @GetMapping("lastElement")
    public ResponseEntity<Long> lastElement(){
        return new ResponseEntity<>(productService.lastElement(),HttpStatus.OK);
    }

    @PostMapping("createOrUpdate")
    public ResponseEntity<Message<ProductDTO>> createOrUpdate(@RequestBody Product product){

        return new ResponseEntity<>(productService.createOrUpdate(product),HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/category/{text}")
    public ResponseEntity<PageImpl> getProductsByCategory(
            @PathVariable String text,
            @RequestParam(defaultValue = "0") int page,   // Parámetro de página con valor por defecto 0
            @RequestParam(defaultValue = "10") int size, // Parámetro de tamaño con valor por defecto 10
            @RequestParam(defaultValue = "") String category   
    ) {
        // Llamar al servicio para obtener los productos paginados
        PageImpl result = productService.getProductsByCategory(category,text, page, size);
        return ResponseEntity.ok(result);  // Devolver el resultado en la respuesta
    }

    @PostMapping("category/product")
    public ResponseEntity<ProductDTO> deleteCategoryInProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.deleteCategoryInProduct(product), HttpStatus.OK);
    }

}
