package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProductDTO;
import com.servidor.gestiondeventas.repository.products.DescriptionProductRepository;
import com.servidor.gestiondeventas.services.products.DescriptionProductService;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DescriptionProductServiceImpl implements DescriptionProductService {
    final private DescriptionProductRepository descriptionProductRepository;
    
    @Override
    public DescriptionProductDTO getDescriptionByIdProduct(Long idProduct) {
        DescriptionProduct descriptionProduct = descriptionProductRepository.findFirstByProduct_Id(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("DescriptionProduct not found with id " + idProduct));

        return new DescriptionProductDTO().fromEntity(descriptionProduct);
    }

    @Override
    public DescriptionProductDTO createDescriptionProduct(DescriptionProduct descriptionProduct) {
        return new DescriptionProductDTO().fromEntity(descriptionProductRepository.save(descriptionProduct));
    }

    @Override
    public boolean deleteDescriptionProduct(Long id) {
        Optional<DescriptionProduct> descriptionProduct = descriptionProductRepository.findById(id);
        if(descriptionProduct.isPresent()){
            descriptionProductRepository.deleteById(id);
            return true;
        }
        return false;
    }
}