package com.eventos.controller;

import com.eventos.entity.Usuario;
import com.eventos.service.EventoService;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
        model.addAttribute("admin", usuario(authentication));
        model.addAttribute("paginaActual", "index");
        model.addAttribute("eventosDestacados", eventoService.obtenerTop5Eventos());
        return "/admin/dashboard";
    }
}
