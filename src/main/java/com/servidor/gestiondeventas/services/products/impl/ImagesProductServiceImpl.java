package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.products.images.ImagesProduct;
import com.servidor.gestiondeventas.repository.products.ImagesProductRepository;
import com.servidor.gestiondeventas.services.products.ImagesProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImagesProductServiceImpl implements ImagesProductService {
    private final ImagesProductRepository imagesProductRepository;
    @Override
    public ImagesProduct getImagesProduct(Long idProd) {
        return imagesProductRepository.findByIdProd(idProd).orElse(null);
    }

    @Override
    public ImagesProduct createImagesProduct(ImagesProduct imagesProduct) {
        Optional<ImagesProduct> imagesProduct1 = imagesProductRepository.findByIdProd(imagesProduct.getIdProd());
        if(imagesProduct1.isEmpty()){
            imagesProductRepository.save(imagesProduct);
        }
        return null;

    }

    @Override
    public ImagesProduct updateImagesProduct(ImagesProduct imagesProduct) {
        Optional<ImagesProduct> imagesProduct1 = imagesProductRepository.findByIdProd(imagesProduct.getIdProd());

        if(imagesProduct1.isPresent()){
            return imagesProductRepository.save(imagesProduct);
        }
        return null;
    }

    @Override
    public boolean deleteImagesProduct(Long id) {
        Optional<ImagesProduct> imagesProduct = imagesProductRepository.findById(id);
        if(imagesProduct.isPresent()){
            imagesProductRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
