package com.eventos.service;

import com.eventos.entity.Evento;
import org.springframework.stereotype.Service;

@Service
public interface InscripcionService {

    int obtenerNumeroDeIncripcionerPorEvento(Evento evento);
}
