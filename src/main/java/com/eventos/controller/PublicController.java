package com.eventos.controller;

import com.eventos.entity.Usuario;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PublicController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    String index(){
        return "/index";
    }

    @GetMapping("/login")
    String login(){
        return "/login";
    }

    @GetMapping("/registro")
    String registro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/registro";
    }

    @PostMapping("/registro")
    String registrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes){
        if(!usuarioService.registrarUsuario(usuario)){
            redirectAttributes.addFlashAttribute("yaExiste", true);
            return "redirect:/registro";
        }else{
            redirectAttributes.addFlashAttribute("registrado", true);
        }
        return "redirect:/login";
    }

    @GetMapping("/403")
    String pagina403(){
        return "/403";
    }
}
