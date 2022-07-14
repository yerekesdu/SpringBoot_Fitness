package kz.springboot.springbootFitness.repositories;

import kz.springboot.springbootFitness.entities.Prices;
import kz.springboot.springbootFitness.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

//    List<Prices> getAllPrices();

}
