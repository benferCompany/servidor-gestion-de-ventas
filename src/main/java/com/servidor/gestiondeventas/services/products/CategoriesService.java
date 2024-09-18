package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;

import java.util.List;

public interface CategoriesService {
    public List<CategoriesDTO> getCategories();

    public Categories createCategory(Categories categories);

    public Categories editCategory(Categories categories);

    public boolean deleteCategory(Long id);
    public Categories getCategoryByName(String name);

}
