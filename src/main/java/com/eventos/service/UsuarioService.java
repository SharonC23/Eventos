package com.eventos.service;

import com.eventos.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    boolean registrarUsuario(Usuario usuario);

    Usuario obtenerPorEmail(String email);
}
