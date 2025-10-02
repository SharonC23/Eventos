package com.eventos.repository;

import com.eventos.entity.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagenes, Long> {
    Optional<Imagenes> findByRuta(String ruta);
}
