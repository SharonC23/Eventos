package com.eventos.controller;

import com.eventos.entity.Evento;
import com.eventos.entity.Imagenes;
import com.eventos.service.CategoriaService;
import com.eventos.service.EventoService;
import com.eventos.service.ImagenService;
import com.eventos.specification.EventoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin/eventos")
public class EventoController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    EventoService eventoService;

    @Autowired
    ImagenService imagenService;

    @GetMapping("/")
    public String eventos(
            Model model,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "categoria", required = false) String categoria,
            @RequestParam(name = "estado", required = false) String estado
    ){
        model.addAttribute("categorias", categoriaService.obtenerTodas());
        model.addAttribute("eventos", eventoService.listarConParticipacion(nombre, categoria, estado));
        model.addAttribute("paginaActual", "eventos");
        return "admin/eventos";
    }

    @PostMapping("/editar")
    public String editarEvento(@ModelAttribute Evento evento){
        eventoService.crearEvento(evento);
        return "redirect:/admin/eventos/";
    }

    @PostMapping("/eliminar")
    public String eliminarEvento(@RequestParam Long id){
        eventoService.eliminarEvento(id);
        return "redirect:/admin/eventos/";
    }

    @PostMapping("/crear")
    public String crearEvento(@ModelAttribute Evento evento, @RequestParam(name = "imagenesInsertar")MultipartFile[] imagenes) throws IOException {
        evento.setEstado("PROXIMO");
        Evento eventoGuardado = eventoService.crearEvento(evento);

        //este es el proceso para insertar una imagen. primero se guarda la ruta que se va a usar (carpeta imagenes en la raiz del proyecto).
        //despues se guarda la ruta de la carpeta donde iran las imagenes en una variable Path y se verifica si existe, si es que no existe. se crea una nueva carpeta en esa ruta.
        //despues se agarra el nombre original del archivo(con .getOriginalFineName()) y se le asigna un random UUID(identificador unico) para evitar nombres repetidos
        //despues se guarda el path que es la ruta + el nombre con identificador unico de la imagen, despues se inserta esa imagen en la carpeta con Files.copy
        //por ultimo se crea un objeto de imagen y se guarda en bd normalmente.

        String carpeta = "imagenes/";
        Path carpetaImagenes = Paths.get(carpeta);

        if (!Files.exists(carpetaImagenes)) {
            Files.createDirectories(carpetaImagenes);
        }

        for(MultipartFile imagen : imagenes){
            if (!imagen.isEmpty()){
                try {
                    String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
                    Path ruta = Paths.get("imagenes").resolve(nombreArchivo);
                    Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                    Imagenes imagenInstertar = new Imagenes();
                    imagenInstertar.setRuta("/imagenes/" + nombreArchivo);
                    imagenInstertar.setEvento(eventoGuardado);

                    imagenService.crearImagen(imagenInstertar);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        eventoService.crearEvento(eventoGuardado);

        return "redirect:/admin/eventos/";
    }
}
