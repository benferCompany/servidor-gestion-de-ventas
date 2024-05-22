package com.servidor.gestiondeventas.services.images;

import com.servidor.gestiondeventas.entities.images.Images;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;

import java.util.List;

public interface ImagesService {
    public List<Images> getImages();
    public Images getImage(Long id);
    public ItemSearchResult getImagesByName(String name, int page, int size);
    public Message<Images> createImage(Images images);
    public Images uploadImage(Images images);
    public boolean deleteImage(Long id);

}
