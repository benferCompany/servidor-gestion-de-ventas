package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.CategoriesDTO;
import com.servidor.gestiondeventas.repository.products.CategoriesRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.services.products.CategoriesService;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<CategoriesDTO> getCategories() {

        return categoriesRepository.findAll().stream()
                .map(CategoriesDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Message<CategoriesDTO> createCategory(Categories categories) {
        Optional<Categories> categories1 = categoriesRepository.findByName(categories.getName());
        Message<CategoriesDTO> message = new Message<CategoriesDTO>();
        if(categories1.isPresent()){
                message.setEntity(CategoriesDTO.fromEntity(categories));
                message.setStatus("false");
                message.setMessage("Ya tienes una categoría con ese nombre.");
                return message;
        }
        message.setEntity(CategoriesDTO.fromEntity(categoriesRepository.save(categories)));
        message.setStatus("true");
        message.setMessage("La categoría se creó con éxito.");

        return message;
    }

    @Override
    public Message<CategoriesDTO> editCategory(Categories categories) {
        Optional<Categories> categories1 = categoriesRepository.findById(categories.getId());
        Message<CategoriesDTO> message = new Message<>();
        if((categories1.isPresent())){
            message.setEntity(CategoriesDTO.fromEntity(categoriesRepository.save(categories)));
            message.setStatus("true");
            message.setMessage("Se editó la categoría exitosamente.");

        }else{
            message.setEntity(CategoriesDTO.fromEntity(categories));
            message.setMessage("La categoría "+categories.getName()+" no existe.");
            message.setStatus("false");
        }
        return message;

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

    @Override
    public Message<CategoriesDTO> deleteCategoryFreeProducts(Categories category){
        Message<CategoriesDTO> message = new Message<>();
        List<Categories> categoriesParent = categoriesRepository.findByParentCategory_Name(category.getName());
        List<Categories> categories = new ArrayList<Categories>();
        categories.add(category);
       List<Product> products = productRepository.findByCategoriesIn(categories);
       if(products.isEmpty() && categoriesParent.isEmpty()){
           categoriesRepository.deleteById(category.getId());
           message.setEntity(CategoriesDTO.fromEntity(category));
           message.setMessage("La categoría "+ category.getName()+" fué eliminada con éxito.");
           message.setStatus("true");

       }else if(!categoriesParent.isEmpty()){
           message.setEntity(CategoriesDTO.fromEntity(category));
           message.setMessage("La categoría "+ category.getName()+" es una categoría padre.");
           message.setStatus("false");

       }else{
           message.setEntity(CategoriesDTO.fromEntity(category));
           message.setMessage("La categoría "+ category.getName()+" tiene productos asociados.");
           message.setStatus("false");

       }
        return message;
    }
    @Override
    public List<CategoriesDTO> getCategoriesParentName(String name){
        return categoriesRepository.findByParentCategory_Name(name).stream().map(CategoriesDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<CategoriesDTO> getCategoriesByParentCategoryIsNull(){
        return categoriesRepository.findByParentCategoryIsNull().stream()
            .map(CategoriesDTO::fromEntity).collect(Collectors.toList());
    }
}
