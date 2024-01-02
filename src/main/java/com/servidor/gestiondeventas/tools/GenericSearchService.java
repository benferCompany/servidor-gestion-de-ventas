package com.servidor.gestiondeventas.tools;

import javax.persistence.*;
import javax.persistence.criteria.*;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GenericSearchService<T> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public Map<String, Object> getEntitiesBySearchTerms(String text, String[] searchFields, int page, int size) {
        String[] searchTerms = text.split("\\s+");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        List<Predicate> predicates = new ArrayList<>();

        for (String searchTerm : searchTerms) {
            List<Predicate> fieldPredicates = new ArrayList<>();
            for (String searchField : searchFields) {
                Expression<String> fieldExpression = criteriaBuilder.lower(root.get(searchField));
                fieldPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(fieldExpression), "%" + searchTerm.toLowerCase() + "%"));
            }
            predicates.add(criteriaBuilder.or(fieldPredicates.toArray(new Predicate[0])));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page * size)
                .setMaxResults(size);

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(entityClass)));
        countQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        Long totalElements = entityManager.createQuery(countQuery).getSingleResult();

        Map<String, Object> result = new HashMap<>();
        result.put("totalElements", totalElements);
        result.put("resultQuery", typedQuery.getResultList());
        return result;
    }
}
