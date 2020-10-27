package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.exception.RateNotFoundException;
import com.senac.assister.backend.domain.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RateService implements CrudService<Rate> {

    private final RateRepository repository;

    public RateService(RateRepository repository){
        this.repository = repository;
    }

    @Override
    public Rate save(Rate source) {
        return repository.save(source);
    }

    @Override
    public Rate delete(UUID id) {
        Rate rate = repository.findById(id).orElseThrow(() -> new RateNotFoundException(id));

        rate.setActive(false);

        return repository.save(rate);
    }

    @Override
    public Rate update(Rate source) {
        return repository.save(source);
    }

    @Override
    public Rate findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RateNotFoundException(id));
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll();
    }
}
