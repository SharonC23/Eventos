package com.eventos.service;

import com.eventos.entity.Evento;
import com.eventos.entity.InscripcionEvento;
import com.eventos.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface InscripcionService {

    int obtenerNumeroDeIncripcionerPorEvento(Evento evento);

    boolean validarInscripcion(Usuario usuario, Evento evento);

    void crearInscripcion(InscripcionEvento inscripcionEvento);
}
