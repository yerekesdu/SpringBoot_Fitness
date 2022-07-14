package kz.springboot.springbootFitness.services;

import kz.springboot.springbootFitness.entities.Freezing;

import java.util.List;

public interface FreezingService {

    Freezing addFreezing(Freezing freezing);

    Freezing saveFreezing(Freezing freezing);

    void deleteFreezing(Freezing freezing);

    Freezing getFreezing(Long id);

    List<Freezing> getAllFreezing();

}
