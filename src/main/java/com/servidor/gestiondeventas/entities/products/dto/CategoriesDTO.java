package com.servidor.gestiondeventas.entities.products.dto;

import com.servidor.gestiondeventas.entities.products.Categories;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class CategoriesDTO {
    private Long id;
    private String name;
    private Categories parentCategory;
    private List<Categories> subCategories = new ArrayList<>();

    public static CategoriesDTO fromEntity(Categories categories, List<Categories> allCategories){
        CategoriesDTO categoriesDTO = new CategoriesDTO();

        categoriesDTO.setId(categories.getId());
        categoriesDTO.setName(categories.getName());
        categoriesDTO.setParentCategory(categories.getParentCategory());

        if (categories.getId() != null && allCategories != null) {
            List<Categories> categories1 = allCategories.stream()
                    .filter(cat -> cat != null &&
                            cat.getParentCategory() != null &&
                            cat.getParentCategory().getId().equals(categories.getId()))
                    .collect(Collectors.toList());

            categoriesDTO.setSubCategories(categories1);
        }
        return categoriesDTO;
    }
}
