package com.eventos.service;

import com.eventos.entity.Evento;
import com.eventos.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {

    Evento crearEvento(Evento evento);

    Evento obtenerPorId(Long id);

    List<Evento> obtenerTodos();

    List<Evento> obtenerTop5Eventos();

    void eliminarEvento(Long id);

    List<Evento> listarConParticipacion(String nombre, String categoria, String estado);
}
