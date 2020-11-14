package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.exception.RateNotFoundException;
import com.senac.assister.backend.domain.exception.SpecialNeedTypeNotFoundException;
import com.senac.assister.backend.domain.repository.RateRepository;
import com.senac.assister.backend.domain.repository.SpecialNeedTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecialNeedTypeService implements CrudService<SpecialNeedType> {

    private final SpecialNeedTypeRepository repository;

    public SpecialNeedTypeService(SpecialNeedTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpecialNeedType save(SpecialNeedType source) {
        return repository.save(source);
    }

    @Override
    public SpecialNeedType delete(UUID id) {
        SpecialNeedType specialNeedType = findById(id);

        repository.delete(specialNeedType);

        return specialNeedType;
    }

    @Override
    public SpecialNeedType update(SpecialNeedType source) {
        return repository.save(source);
    }

    @Override
    public SpecialNeedType findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new SpecialNeedTypeNotFoundException(id));
    }

    public SpecialNeedType findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new SpecialNeedTypeNotFoundException(name));
    }

    @Override
    public List<SpecialNeedType> findAll() {
        return repository.findAll();
    }
}
