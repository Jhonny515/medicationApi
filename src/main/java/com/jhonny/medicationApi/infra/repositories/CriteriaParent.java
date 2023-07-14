package com.jhonny.medicationApi.infra.repositories;

import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class CriteriaParent<E, D> {
    @PersistenceContext
    EntityManager em;

    public List<E> findAllWithCriteriaParent(D paramDTO, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked") Class<E> classType = (Class<E>) (type).getActualTypeArguments()[0]; // TODO: Wow

        CriteriaQuery<E> query = cb.createQuery(classType);
        Root<E> root = query.from(classType);

        List<Predicate> predicates = new ArrayList<>();

        filterAtributesFromEntity(paramDTO, cb, root, predicates, query);
        if(!predicates.isEmpty()) {
            query.where(predicates.toArray(Predicate[]::new));
        }
        TypedQuery<E> queryResult = em.createQuery(query)
                .setMaxResults(pageable.getPageSize())
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

        return queryResult.getResultList();
    }

    protected abstract void filterAtributesFromEntity(D paramDTO, CriteriaBuilder cb, Root<E> root, List<Predicate> predicates, CriteriaQuery<E> query);
}
