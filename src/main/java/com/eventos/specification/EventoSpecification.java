package com.eventos.specification;


import com.eventos.entity.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventoSpecification {

    @PersistenceContext
    EntityManager entityManager;

    public List<Evento> filtrarEventos(
            String nombre,
            String categoria,
            String estado
    ){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> query = criteriaBuilder.createQuery(Evento.class);
        Root<Evento> root = query.from(Evento.class);

        List<Predicate> predicates = new ArrayList<>();

        if(nombre != null && !nombre.isBlank()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }

        if(categoria != null && !categoria.isBlank()){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("categoria").get("nombreCategoria")), "%" + categoria.toLowerCase() + "%"));
        }

        if(estado != null && !estado.isBlank()){
            predicates.add(criteriaBuilder.like(root.get("estado"), estado));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}