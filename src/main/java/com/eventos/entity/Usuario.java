package com.eventos.entity;

import com.eventos.config.enums.RolUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Email
    @NotBlank
    private String email;

    private String nombreCompleto;

    @NotBlank
    private String password;

    private String cedula;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
}