package com.jhonny.medicationApi.builders;

/**
 * Intefarce with generics to avoid use type cast on implemented class
 * @param <E> Entity
 * @param <D> DTO
 */
public interface MainBuilder<E, D> {
    D entityToDTO(E entity);

    E dtoToEntity(D dto);
}
