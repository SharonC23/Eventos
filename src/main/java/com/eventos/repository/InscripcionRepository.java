package com.eventos.repository;

import com.eventos.entity.Evento;
import com.eventos.entity.InscripcionEvento;
import com.eventos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEvento, Long> {
    int countByEvento(Evento evento);

    boolean existsByUsuarioAndEvento(Usuario usuario, Evento evento);

    List<InscripcionEvento> findByUsuarioAndEventoEstado(Usuario usuario, String estado);

    List<InscripcionEvento> findByUsuario(Usuario usuario);
}
