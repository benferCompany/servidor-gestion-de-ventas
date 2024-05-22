package com.servidor.gestiondeventas.repository.receipts;

import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailProductsRepository extends JpaRepository<DetailProduct,Long> {

}
