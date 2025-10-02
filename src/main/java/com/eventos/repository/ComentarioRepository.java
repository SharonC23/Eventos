package com.eventos.repository;

import com.eventos.entity.Comentario;
import com.eventos.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    int countByEvento(Evento evento);
}
