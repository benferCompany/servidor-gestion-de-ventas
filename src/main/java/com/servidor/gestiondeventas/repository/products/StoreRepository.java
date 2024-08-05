package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT p FROM Store p WHERE p.company.id = :companyId")
    List<Store> findByCompanyId(@Param("companyId") Long companyId);
}
