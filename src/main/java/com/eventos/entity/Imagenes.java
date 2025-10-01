package com.eventos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "imagenes")
public class Imagenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;

    private String ruta;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
