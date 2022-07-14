package kz.springboot.springbootFitness.repositories;

import kz.springboot.springbootFitness.entities.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

//    @Query("SELECT new com.baeldung.aggregation.model.custom.CommentCount(c.year, COUNT(c.year)) "
//            + "FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")
//    List<CommentCount> countTotalCommentsByYearClass();
    
}
