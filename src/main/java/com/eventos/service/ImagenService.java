package com.eventos.service;

import com.eventos.entity.Imagenes;
import org.springframework.stereotype.Service;

@Service
public interface ImagenService {

    void crearImagen(Imagenes imagen);
}
