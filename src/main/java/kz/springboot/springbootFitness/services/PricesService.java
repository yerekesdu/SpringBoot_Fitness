package kz.springboot.springbootFitness.services;

import kz.springboot.springbootFitness.entities.Prices;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PricesService {

    Prices addPrice(Prices price);

    Prices savePrice(Prices prices);

    void deletePrice(Prices prices);

    List<Prices> getAllPrices();

    Prices getPrice(Long id);

}
