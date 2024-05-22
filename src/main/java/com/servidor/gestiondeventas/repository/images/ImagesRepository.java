package com.servidor.gestiondeventas.repository.images;

import com.servidor.gestiondeventas.entities.images.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images,Long> {
    Images findFirstByName(String name);
}
