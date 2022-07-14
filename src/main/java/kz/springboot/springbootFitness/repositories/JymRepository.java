package kz.springboot.springbootFitness.repositories;

import kz.springboot.springbootFitness.entities.Jyms;
import kz.springboot.springbootFitness.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface JymRepository extends JpaRepository<Jyms, Long> {
//    Jyms getGyms getAllByPricesIsNotNull;

    List<Jyms> findDistinctByPricesIsNotNull();
}
