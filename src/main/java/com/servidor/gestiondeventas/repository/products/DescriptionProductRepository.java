package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescriptionProductRepository extends JpaRepository<DescriptionProduct,Long> {
    Optional<DescriptionProduct> findFirstByProduct_Id(Long id);
}
