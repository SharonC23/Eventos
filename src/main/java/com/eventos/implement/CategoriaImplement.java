package com.eventos.implement;

import com.eventos.entity.CategoriaEvento;
import com.eventos.repository.CategoriaRepository;
import com.eventos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImplement implements CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaEvento> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public void crearCategoria(CategoriaEvento categoriaEvento) {
        categoriaRepository.save(categoriaEvento);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow());
    }

    @Override
    public void editarCategoria(CategoriaEvento categoriaEvento) {
        categoriaRepository.save(categoriaEvento);
    }
}
