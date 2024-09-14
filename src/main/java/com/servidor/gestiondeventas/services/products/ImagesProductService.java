package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.images.ImagesProduct;

public interface ImagesProductService {
    public ImagesProduct getImagesProduct(Long idProd);
    public ImagesProduct createImagesProduct(ImagesProduct imagesProduct);
    public ImagesProduct updateImagesProduct(ImagesProduct imagesProduct);
    public boolean deleteImagesProduct(Long id);
}
