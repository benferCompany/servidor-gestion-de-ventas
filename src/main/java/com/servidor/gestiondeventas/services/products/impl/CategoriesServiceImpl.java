package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;
import com.servidor.gestiondeventas.repository.products.CategoriesRepository;
import com.servidor.gestiondeventas.services.products.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    @Override
    @Transactional
    public List<CategoriesDTO> getCategories() {

        return categoriesRepository.findAll().stream()
                .map(CategoriesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Categories createCategory(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public Categories editCategory(Categories categories) {
        Optional<Categories> categories1 = categoriesRepository.findById(categories.getId());
        if((categories1.isPresent())){
            return categoriesRepository.save(categories);
        }
        return null;

    }

    @Override
    public boolean deleteCategory(Long id) {
        Optional<Categories> category=categoriesRepository.findById(id);
        if(category.isPresent()){
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public Categories getCategoryByName(String name){
        return categoriesRepository.findByName(name).orElse(null);
    }
}
