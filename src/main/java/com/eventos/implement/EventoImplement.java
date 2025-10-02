package com.eventos.implement;

import com.eventos.entity.Evento;
import com.eventos.entity.Usuario;
import com.eventos.repository.EventoRepository;
import com.eventos.repository.InscripcionRepository;
import com.eventos.service.EventoService;
import com.eventos.specification.EventoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoImplement implements EventoService {
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    InscripcionRepository inscripcionRepository;

    @Autowired
    EventoSpecification eventoSpecification;

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
    }

    @Override
    public List<Evento> obtenerTop5Eventos() {
        return eventoRepository.encontrarRankingEventos().stream().limit(5).toList();
    }

    @Override
    public void eliminarEvento(Long id) {
        eventoRepository.delete(eventoRepository.findById(id).orElseThrow());
    }

    @Override
    public List<Evento> listarConParticipacion(String nombre, String categoria, String estado) {
        List<Evento> eventos = eventoSpecification.filtrarEventos(nombre, categoria, estado);

        for (Evento evento : eventos) {
            Long inscritos = inscripcionRepository.countByEvento(evento);
            Long asistieron = inscripcionRepository.countByEventoAndAsistenciaTrue(evento);

            evento.setTotalInscritos(inscritos);
            evento.setTotalAsistieron(asistieron);

            double porcentaje = (inscritos == 0) ? 0.0 : (asistieron * 100.0) / inscritos;
            evento.setPorcentajeParticipacion(porcentaje);
        }

        return eventos;
    }
}
