package com.eventos.service;

import com.eventos.entity.CategoriaEvento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {

    List<CategoriaEvento> obtenerTodas();

    void crearCategoria(CategoriaEvento categoriaEvento);

    void eliminarCategoria(Long id);

    void editarCategoria(CategoriaEvento categoriaEvento);
}
