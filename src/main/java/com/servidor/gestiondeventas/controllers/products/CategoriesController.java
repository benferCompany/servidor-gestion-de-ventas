package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;
import com.servidor.gestiondeventas.services.products.CategoriesService;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {
    private  final CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> getCategories(){
        return new ResponseEntity<>(categoriesService.getCategories(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Message<CategoriesDTO>> createCategory(@RequestBody Categories category){
        return  new ResponseEntity<>(categoriesService.createCategory(category),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Message<CategoriesDTO>> editCategory(@RequestBody Categories category){
        return new ResponseEntity<>(categoriesService.editCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categoriesService.deleteCategory(id);
    }

    @PostMapping("delete")
    public ResponseEntity<Message<CategoriesDTO>> deleteCategoryFreeProducts(@RequestBody Categories category){
        return new ResponseEntity<>(categoriesService.deleteCategoryFreeProducts(category),HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<CategoriesDTO>> getCategoriesParentName(@PathVariable String name){
        return new ResponseEntity<>(categoriesService.getCategoriesParentName(name),HttpStatus.OK);
    }


}
