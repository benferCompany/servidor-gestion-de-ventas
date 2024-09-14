package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.images.ImagesProduct;
import com.servidor.gestiondeventas.services.products.ImagesProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/imagesProduct")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class imagesProductController {
    private final ImagesProductService imagesProductService;

    @GetMapping("/{idProd}")
    public ResponseEntity<ImagesProduct> getImagesProduct(@PathVariable Long idProd){
        return new ResponseEntity<>(imagesProductService.getImagesProduct(idProd), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ImagesProduct> createImagesProduct(@RequestBody ImagesProduct imagesProduct){
        return new ResponseEntity<>(imagesProductService.createImagesProduct(imagesProduct), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ImagesProduct> updateImagesProduct(@RequestBody ImagesProduct imagesProduct){
        return new ResponseEntity<>(imagesProductService.updateImagesProduct(imagesProduct),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public boolean deleteImagesProduct(@PathVariable Long id){
        return imagesProductService.deleteImagesProduct(id);
    }
}
