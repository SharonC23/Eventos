package com.eventos.controller;

import com.eventos.entity.Evento;
import com.eventos.entity.Imagenes;
import com.eventos.entity.Usuario;
import com.eventos.service.EventoService;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ciudadanos")
public class CiudadanoController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EventoService eventoService;

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
    public String detalleEvento(Model model, @PathVariable("id") Long idEvento){
        model.addAttribute("evento", eventoService.obtenerPorId(idEvento));
        return "/ciudadano/detalle";
    }
}
