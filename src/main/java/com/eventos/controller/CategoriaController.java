package com.eventos.controller;

import com.eventos.entity.CategoriaEvento;
import com.eventos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public String categorias(Model model, Authentication authentication){
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        model.addAttribute("paginaActual", "categorias");
        return "admin/categorias";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute CategoriaEvento categoriaEvento){
        categoriaService.crearCategoria(categoriaEvento);
        return "redirect:/admin/categorias/";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return "redirect:/admin/categorias/";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute CategoriaEvento categoriaEvento){
        categoriaService.editarCategoria(categoriaEvento);
        return "redirect:/admin/categorias/";
    }
}
