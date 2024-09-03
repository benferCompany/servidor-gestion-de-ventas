package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProductDTO;
import com.servidor.gestiondeventas.services.products.DescriptionProductService;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/descriptionProduct")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DescriptionProductController {
    private final DescriptionProductService     descriptionProductService;
    @GetMapping("/{id}")
    public ResponseEntity<DescriptionProductDTO> getDescriptionProductById(@PathVariable Long id){
        return ResponseEntity.ok(descriptionProductService.getDescriptionByIdProduct(id));
    }

    @PostMapping
    public ResponseEntity<DescriptionProductDTO> createDescriptionProduct(@RequestBody DescriptionProduct descriptionProduct){
        return new ResponseEntity<>(descriptionProductService.createDescriptionProduct(descriptionProduct),HttpStatus.CREATED);
    }
}
