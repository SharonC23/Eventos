package com.eventos.implement;

import com.eventos.entity.Evento;
import com.eventos.repository.EventoRepository;
import com.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoImplement implements EventoService {
    @Autowired
    EventoRepository eventoRepository;

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    @Override
    public void eliminarEvento(Long id) {
        eventoRepository.delete(eventoRepository.findById(id).orElseThrow());
    }
}
