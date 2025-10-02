package com.eventos.implement;

import com.eventos.entity.Comentario;
import com.eventos.entity.Evento;
import com.eventos.repository.ComentarioRepository;
import com.eventos.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioImplement implements ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;

    @Override
    public int totalComentariosPorEvento(Evento evento) {
        return comentarioRepository.countByEvento(evento);
    }

    @Override
    public void crearComentario(Comentario comentario) {
        comentarioRepository.save(comentario);
    }
}
