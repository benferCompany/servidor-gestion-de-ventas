package com.servidor.gestiondeventas.repository.products;

import com.servidor.gestiondeventas.entities.products.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByDescriptionContaining(String term);

    @Query(value = "SELECT * FROM product WHERE to_tsvector('english', description) @@ to_tsquery('english', ?1 || ':*')", nativeQuery = true)
    List<Product> searchByProductNameRegex(String regex);

    @Query(value = "SELECT * FROM product WHERE to_tsvector('spanish', description) @@ to_tsquery('spanish', ?1) OR description ~ ?2", nativeQuery = true)
    List<Product> searchByProductNameFullTextAndRegex(String tsQuery, String regex);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.description) LIKE LOWER(:likeQuery)")
    List<Product> searchByProductNameLike(@Param("likeQuery") String likeQuery);

    List<Product> findByDescription(String name);
}
