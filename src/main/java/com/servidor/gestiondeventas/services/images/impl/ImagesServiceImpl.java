package com.servidor.gestiondeventas.services.images.impl;

import com.servidor.gestiondeventas.entities.images.Images;
import com.servidor.gestiondeventas.repository.images.ImagesRepository;
import com.servidor.gestiondeventas.services.images.ImagesService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImagesServiceImpl implements ImagesService {
    final private ImagesRepository imagesRepository;
    final private EntityManager entityManager;
    @Override
    public List<Images> getImages() {
        return imagesRepository.findAll();
    }

    @Override
    public Images getImage(Long id) {
        Optional<Images> image = imagesRepository.findById(id);
        return image.orElse(null);
    }

    @Override
    public ItemSearchResult getImagesByName(String name,  int page, int size) {
        GenericSearchService<Images> genericSearchService = new GenericSearchService<>(entityManager, Images.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(name, new String[]{"name","value"}, page, size);
        List<Images> images = (List<Images>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");

        return new ItemSearchResult<>(images, totalElements);

    }

    @Override
    public Message<Images> createImage(Images images) {
        Images returnImage = imagesRepository.findFirstByName(images.getName());
        // Verificar si ya existe una imagen con el mismo nombre
        if (returnImage == null) {
            // Guardar la imagen en la base de datos
            Images savedImage = imagesRepository.save(images);
            // Crear un mensaje con la imagen guardada y un mensaje de éxito
            return new Message<>(savedImage, "La imagen se guardó con éxito", "CREATED");
        } else {
            // Si ya existe una imagen con el mismo nombre, retornar un mensaje de error
            return new Message<>(returnImage, "Ya existe una imagen con el mismo nombre", "FALSE");
        }
    }

    @Override
    public Images uploadImage(Images images) {
        Optional<Images> image = imagesRepository.findById(images.getId());
        if(image.isPresent()){
            return imagesRepository.save(images);
        }
        return null;

    }

    @Override
    public boolean deleteImage(Long id) {
      if( imagesRepository.findById(id).isPresent()){
        imagesRepository.deleteById(id);
        return true;
      }
      return false;


    }
}
