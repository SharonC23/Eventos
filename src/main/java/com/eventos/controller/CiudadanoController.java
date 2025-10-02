package com.eventos.controller;

import com.eventos.entity.Evento;
import com.eventos.entity.Imagenes;
import com.eventos.entity.InscripcionEvento;
import com.eventos.entity.Usuario;
import com.eventos.service.ComentarioService;
import com.eventos.service.EventoService;
import com.eventos.service.InscripcionService;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ciudadanos")
public class CiudadanoController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EventoService eventoService;

    @Autowired
    InscripcionService inscripcionService;

    @Autowired
    ComentarioService comentarioService;

    @ModelAttribute
    public Usuario usuario(Authentication authentication){
        return usuarioService.obtenerPorEmail(authentication.getName());
    }

    @GetMapping("/index")
    public String index(Model model, Authentication authentication){
        model.addAttribute("ciudadano", usuario(authentication));
        return "/ciudadano/dashboard";
    }

    @GetMapping("/eventos")
    public String eventos(Model model){
        model.addAttribute("eventos", eventoService.obtenerTodos());
        return "/ciudadano/eventos";
    }

    @GetMapping("/evento/{id}")
    public String detalleEvento(Model model, @PathVariable("id") Long idEvento, Authentication authentication){
        Evento evento = eventoService.obtenerPorId(idEvento);
        Usuario usuario = usuario(authentication);

        model.addAttribute("usuario", usuario);
        model.addAttribute("totalInscripciones", inscripcionService.obtenerNumeroDeIncripcionerPorEvento(evento));
        model.addAttribute("totalComentarios", comentarioService.totalComentariosPorEvento(evento));
        model.addAttribute("inscrito", inscripcionService.validarInscripcion(usuario, evento));
        model.addAttribute("evento", eventoService.obtenerPorId(idEvento));
        return "/ciudadano/detalle";
    }

    @PostMapping("/evento/inscribirse/{id}")
    public String inscribirse(Model model, @PathVariable("id") Long idEvento, @RequestParam(name = "telefono") String telefono, Authentication authentication){
        Evento evento = eventoService.obtenerPorId(idEvento);
        Usuario usuario = usuario(authentication);

        InscripcionEvento inscripcionEvento = new InscripcionEvento();

        inscripcionEvento.setEvento(evento);
        inscripcionEvento.setFechaInscripcion(LocalDateTime.now());
        inscripcionEvento.setTelefono(telefono);
        inscripcionEvento.setUsuario(usuario);

        inscripcionService.crearInscripcion(inscripcionEvento);
        return "redirect:/ciudadanos/evento/"+idEvento;
    }
}
