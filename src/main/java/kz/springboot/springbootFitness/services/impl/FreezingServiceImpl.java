package kz.springboot.springbootFitness.services.impl;

import kz.springboot.springbootFitness.entities.Freezing;
import kz.springboot.springbootFitness.repositories.FreezingRepository;
import kz.springboot.springbootFitness.services.FreezingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreezingServiceImpl implements FreezingService {

    @Autowired
    private FreezingRepository freezingRepository;

    @Override
    public Freezing addFreezing(Freezing freezing) {
        return freezingRepository.save(freezing);
    }

    @Override
    public Freezing saveFreezing(Freezing freezing) {
        return freezingRepository.save(freezing);
    }

    @Override
    public void deleteFreezing(Freezing freezing) {
        freezingRepository.delete(freezing);
    }

    @Override
    public Freezing getFreezing(Long id) {
        return freezingRepository.getById(id);
    }

    @Override
    public List<Freezing> getAllFreezing() {
        return freezingRepository.findAll();
    }
}
