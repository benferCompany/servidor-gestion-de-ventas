package com.servidor.gestiondeventas.tools;

import javax.persistence.*;
import javax.persistence.criteria.*;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GenericSearchService<T> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;


    public List<T> getEntitiesBySearchTerms(String text, String... searchFields) {
        String[] searchTerms = text.split("\\s+");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();

        for (String searchTerm : searchTerms) {
            List<Predicate> fieldPredicates = new ArrayList<>();
            for (String searchField : searchFields) {
                // Convertir tanto el término de búsqueda como el valor del campo a minúsculas
                Expression<String> fieldExpression = criteriaBuilder.lower(root.get(searchField));
                fieldPredicates.add(criteriaBuilder.like(fieldExpression, "%" + searchTerm + "%"));
            }
            predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery).setMaxResults(20);

        return typedQuery.getResultList();
    }
}