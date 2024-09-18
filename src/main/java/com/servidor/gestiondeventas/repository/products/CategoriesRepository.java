package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    Optional<Categories> findByName(String name);
}
