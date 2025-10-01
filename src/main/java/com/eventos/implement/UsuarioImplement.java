package com.eventos.implement;

import com.eventos.config.enums.RolUsuario;
import com.eventos.entity.Usuario;
import com.eventos.repository.UsuarioRepository;
import com.eventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioImplement implements UsuarioService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean registrarUsuario(Usuario usuario){
        if(usuarioRepository.existsBycedula(usuario.getCedula()) || usuarioRepository.existsByemail(usuario.getEmail())){
            return false;
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(RolUsuario.CIUDADANO);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow();
    }
}
