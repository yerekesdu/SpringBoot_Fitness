package kz.springboot.springbootFitness.services.impl;

import kz.springboot.springbootFitness.entities.Trainers;
import kz.springboot.springbootFitness.repositories.TrainerRepository;
import kz.springboot.springbootFitness.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainersServiceImpl implements TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    @Override
    public Trainers addTrainer(Trainers trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainers saveTrainer(Trainers trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(Trainers trainer) {
        trainerRepository.delete(trainer);
    }

    @Override
    public Trainers getTrainer(Long id) {
        return trainerRepository.getById(id);
    }

    @Override
    public List<Trainers> getAllTrainers() {
        return trainerRepository.findAll();
    }
}
