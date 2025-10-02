package com.eventos.controller;

import com.eventos.entity.Comentario;
import com.eventos.service.ComentarioService;
import com.eventos.service.EventoService;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ciudadanos/comentarios")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EventoService eventoService;

    @PostMapping("/comentar")
    public String comentar(@RequestParam(name = "idEvento") Long idEvento, @RequestParam(name = "mensaje") String mensaje, Authentication authentication){
        Comentario comentario = new Comentario();

        comentario.setFecha(LocalDateTime.now());
        comentario.setEvento(eventoService.obtenerPorId(idEvento));
        comentario.setUsuario(usuarioService.obtenerPorEmail(authentication.getName()));
        comentario.setMensaje(mensaje);

        comentarioService.crearComentario(comentario);
        return "redirect:/ciudadanos/evento/"+idEvento;
    }
}
