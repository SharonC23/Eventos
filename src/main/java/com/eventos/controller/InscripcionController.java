package com.eventos.controller;

import com.eventos.entity.InscripcionEvento;
import com.eventos.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/inscripciones")
public class InscripcionController {

    @Autowired
    InscripcionService inscripcionService;

    @GetMapping("/")
    public String inscripciones(Model model, Authentication authentication){
        model.addAttribute("inscripciones", inscripcionService.obtenerTodas());
        return "/admin/inscripciones";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarInscripciones(@PathVariable("id") Long idAsistencia){
        InscripcionEvento inscripcionEvento = inscripcionService.obtenerPorId(idAsistencia);
        inscripcionService.eliminar(inscripcionEvento);
        return "redirect:/admin/inscripciones/";
    }

    @PostMapping("/editar/{id}")
    public String inscripciones(@PathVariable("id") Long idAsistencia, @RequestParam(name = "asistencia", required = false) boolean asistencia){
        InscripcionEvento inscripcionEvento = inscripcionService.obtenerPorId(idAsistencia);
        inscripcionEvento.setAsistencia(asistencia);
        inscripcionService.crearInscripcion(inscripcionEvento);
        return "redirect:/admin/inscripciones/";
    }
}
