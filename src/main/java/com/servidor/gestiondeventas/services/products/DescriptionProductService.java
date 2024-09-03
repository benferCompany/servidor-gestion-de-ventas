package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProductDTO;
import com.servidor.gestiondeventas.tools.Message;

public interface DescriptionProductService {
    public DescriptionProductDTO getDescriptionByIdProduct(Long idProduct);
    public DescriptionProductDTO createDescriptionProduct(DescriptionProduct descriptionProduct);
}
