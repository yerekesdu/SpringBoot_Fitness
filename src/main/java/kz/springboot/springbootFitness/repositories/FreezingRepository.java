package kz.springboot.springbootFitness.repositories;

import kz.springboot.springbootFitness.entities.Freezing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface FreezingRepository extends JpaRepository<Freezing, Long> {
}
