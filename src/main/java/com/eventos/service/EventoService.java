package com.eventos.service;

import com.eventos.entity.Evento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {

    Evento crearEvento(Evento evento);

    List<Evento> obtenerTodos();

    void eliminarEvento(Long id);
}
