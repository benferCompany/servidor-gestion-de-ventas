package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
}
