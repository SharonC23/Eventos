package com.eventos.implement;

import com.eventos.entity.Imagenes;
import com.eventos.repository.ImagenRepository;
import com.eventos.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenImplement implements ImagenService {
    @Autowired
    ImagenRepository imagenRepository;

    @Override
    public void crearImagen(Imagenes imagen) {
        imagenRepository.save(imagen);
    }
}
