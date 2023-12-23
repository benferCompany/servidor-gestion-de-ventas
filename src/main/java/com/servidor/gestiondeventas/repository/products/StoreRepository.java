package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
