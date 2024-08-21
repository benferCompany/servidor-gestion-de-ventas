package com.servidor.gestiondeventas.controllers.products;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;
import com.servidor.gestiondeventas.services.products.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {
    private  final CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> getCategories(){
        return new ResponseEntity<>(categoriesService.getCategories(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody Categories category){
        return  new ResponseEntity<>(categoriesService.createCategory(category),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Categories> editCategory(@RequestBody Categories category){
        return new ResponseEntity<>(categoriesService.editCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categoriesService.deleteCategory(id);
    }


}
