package com.eventos.implement;

import com.eventos.entity.Evento;
import com.eventos.entity.InscripcionEvento;
import com.eventos.entity.Usuario;
import com.eventos.repository.InscripcionRepository;
import com.eventos.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionImplement implements InscripcionService {
    @Autowired
    InscripcionRepository inscripcionRepository;

    @Override
    public int obtenerNumeroDeIncripcionerPorEvento(Evento evento) {
        return inscripcionRepository.countByEvento(evento);
    }

    @Override
    public boolean validarInscripcion(Usuario usuario, Evento evento) {
        return inscripcionRepository.existsByUsuarioAndEvento(usuario, evento);
    }

    @Override
    public void crearInscripcion(InscripcionEvento inscripcionEvento) {
        inscripcionRepository.save(inscripcionEvento);
    }
}
