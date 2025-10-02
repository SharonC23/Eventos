package com.eventos.repository;

import com.eventos.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e LEFT JOIN e.inscripciones i GROUP BY e ORDER BY COUNT(i) DESC")
    List<Evento> encontrarRankingEventos();

    Optional<Evento> findByNombre(String nombre);
}
