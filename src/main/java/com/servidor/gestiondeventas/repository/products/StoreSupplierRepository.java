package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreSupplierRepository extends JpaRepository<StoreSupplier, Long> {

    @Query(value = "SELECT * FROM store_supplier WHERE id_supplier_one = :idSupplierOne", nativeQuery = true)
    List<StoreSupplier> selectByIdSupplierOne(@Param("idSupplierOne") String idSupplierOne);

}
