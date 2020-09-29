package com.senac.assister.backend.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudService<T> {

    T save(T source);

    T delete(UUID id);

    T update(T source);

    T findById(UUID id);

    List<T> findAll();

}