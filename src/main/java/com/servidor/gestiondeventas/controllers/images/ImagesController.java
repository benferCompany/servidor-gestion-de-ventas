package com.servidor.gestiondeventas.controllers.images;

import com.servidor.gestiondeventas.entities.images.Images;
import com.servidor.gestiondeventas.services.images.ImagesService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImagesController {
    @Autowired
    public ImagesService imagesService;

    @GetMapping
    public List<Images> getImages(){
        return imagesService.getImages();
    }

    @GetMapping("/name")
    public ResponseEntity<Page<Images>> getImages(@RequestParam(name = "name") String name,
                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name ="size", defaultValue = "0") int size){
        ItemSearchResult itemSearchResult =imagesService.getImagesByName(name,page,size);
        List<Images> images = itemSearchResult.getResultList();
        Long totalElements = itemSearchResult.getTotalElements();
        return new ResponseEntity<>(new PageImpl<>(images, PageRequest.of(page, size), totalElements), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Message<Images>> createImage(@RequestBody Images image){
        return new ResponseEntity<> (imagesService.createImage(image), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Images> updateImage(@RequestBody  Images image){
        return new ResponseEntity<>(imagesService.uploadImage(image),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteImage(@PathVariable Long id){
        return imagesService.deleteImage(id);
    }
}
