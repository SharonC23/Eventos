package com.eventos.repository;

import com.eventos.entity.Evento;
import com.eventos.entity.InscripcionEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEvento, Long> {
    int countByEvento(Evento evento);
}
