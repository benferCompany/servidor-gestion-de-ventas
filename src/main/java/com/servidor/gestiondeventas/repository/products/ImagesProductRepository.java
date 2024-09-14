package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.images.ImagesProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagesProductRepository extends JpaRepository<ImagesProduct,Long> {
    Optional<ImagesProduct> findByIdProd(Long idProd);
}

