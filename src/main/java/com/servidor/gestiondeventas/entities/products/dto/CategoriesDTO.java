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

    public static CategoriesDTO fromEntity(Categories categories){
        CategoriesDTO categoriesDTO = new CategoriesDTO();

        categoriesDTO.setId(categories.getId());
        categoriesDTO.setName(categories.getName());
        categoriesDTO.setParentCategory(categories.getParentCategory());

        return categoriesDTO;
    }
}
