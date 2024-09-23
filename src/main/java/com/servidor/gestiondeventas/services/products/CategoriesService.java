package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;
import com.servidor.gestiondeventas.tools.Message;

import java.util.List;

public interface CategoriesService {
    public List<CategoriesDTO> getCategories();

    public Message<CategoriesDTO> createCategory(Categories categories);

    public Message<CategoriesDTO> editCategory(Categories categories);

    public boolean deleteCategory(Long id);
    public Categories getCategoryByName(String name);
    public Message<CategoriesDTO> deleteCategoryFreeProducts(Categories category);
    public List<CategoriesDTO> getCategoriesParentName(String name);

}
