package com.eventos.implement;

import com.eventos.entity.Evento;
import com.eventos.entity.InscripcionEvento;
import com.eventos.entity.Usuario;
import com.eventos.repository.InscripcionRepository;
import com.eventos.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionImplement implements InscripcionService {
    @Autowired
    InscripcionRepository inscripcionRepository;

    @Override
    public boolean validarInscripcion(Usuario usuario, Evento evento) {
        return inscripcionRepository.existsByUsuarioAndEvento(usuario, evento);
    }

    @Override
    public Long obtenerNumeroDeIncripcionerPorEvento(Evento evento) {
        return inscripcionRepository.countByEvento(evento);
    }

    @Override
    public void crearInscripcion(InscripcionEvento inscripcionEvento) {
        inscripcionRepository.save(inscripcionEvento);
    }

    @Override
    public List<InscripcionEvento> obtenerPorUsuarioYEstadoEvento(Usuario usuario, String estado) {
        return inscripcionRepository.findByUsuarioAndEventoEstado(usuario, estado);
    }

    @Override
    public List<InscripcionEvento> obtenerPorUsuario(Usuario usuario) {
        return inscripcionRepository.findByUsuario(usuario);
    }

    @Override
    public List<InscripcionEvento> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    @Override
    public InscripcionEvento obtenerPorId(Long id) {
        return inscripcionRepository.findById(id).orElseThrow();
    }

    @Override
    public void eliminar(InscripcionEvento inscripcionEvento) {
        inscripcionRepository.delete(inscripcionEvento);
    }
}
