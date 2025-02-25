package com.nicolas.Lavadero.service.mapper;

import java.util.List;

public interface EntityMapper<D,Din,E> {
    E toEntity(Din dto);

    D toDto(E entity);

    List<D> toDto(List<E> entityList);

    Iterable<E> toEntity(Iterable<D> dtoList);

    Iterable<D> toDto(Iterable<E> entityList);
}
