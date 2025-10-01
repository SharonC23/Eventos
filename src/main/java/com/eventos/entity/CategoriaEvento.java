package com.eventos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class CategoriaEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaEvento;

    private String nombreCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<Evento> eventos;

    private String color;

    private String descripcion;

    private String emojiBootstrap;
}
