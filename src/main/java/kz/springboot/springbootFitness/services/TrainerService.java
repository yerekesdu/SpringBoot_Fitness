package kz.springboot.springbootFitness.services;

import kz.springboot.springbootFitness.entities.Trainers;

import java.util.List;

public interface TrainerService {

    Trainers addTrainer(Trainers trainer);

    Trainers saveTrainer(Trainers trainer);

    void deleteTrainer(Trainers trainer);

    Trainers getTrainer(Long id);

    List<Trainers> getAllTrainers();

}
