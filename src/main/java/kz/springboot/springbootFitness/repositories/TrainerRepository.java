package kz.springboot.springbootFitness.repositories;

import kz.springboot.springbootFitness.entities.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TrainerRepository extends JpaRepository<Trainers, Long> {

}
