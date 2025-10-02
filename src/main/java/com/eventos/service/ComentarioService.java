package com.eventos.service;

import com.eventos.entity.Comentario;
import com.eventos.entity.Evento;
import org.springframework.stereotype.Service;

@Service
public interface ComentarioService {

    int totalComentariosPorEvento(Evento evento);

    void crearComentario(Comentario comentario);
}
