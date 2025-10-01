package com.eventos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    private String mensaje;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
