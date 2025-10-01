package com.eventos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String nombre;

    private LocalDate fecha;

    private LocalTime hora;

    private String lugar;

    private String descripcion;

    private String estado; //aqui pueden ser proximos, finalizados

    @ManyToOne
    @JoinColumn(name = "id_categoria_evento")
    private CategoriaEvento categoria;

    @OneToMany(mappedBy = "evento")
    private List<Imagenes> imagenes = new ArrayList<>();
}
