package com.eventos.repository;

import com.eventos.entity.CategoriaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEvento, Long> {

    Optional<CategoriaEvento> findByNombreCategoria(String nombreCategoria);
}
