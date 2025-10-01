package com.eventos.repository;

import com.eventos.config.enums.RolUsuario;
import com.eventos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    public boolean existsByemail(String email);

    public boolean existsBycedula(String cedula);

    List<Usuario> findByRol(RolUsuario rolUsuario);
}
