package kz.springboot.springbootFitness.services.impl;

import kz.springboot.springbootFitness.entities.Prices;
import kz.springboot.springbootFitness.repositories.PricesRepository;
import kz.springboot.springbootFitness.services.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    PricesRepository pricesRepository;

    @Override
    public Prices addPrice(Prices price) {
        return pricesRepository.save(price);
    }

    @Override
    public Prices savePrice(Prices prices) {
        return pricesRepository.save(prices);
    }

    @Override
    public void deletePrice(Prices prices) {
        pricesRepository.delete(prices);
    }

    @Override
    public List<Prices> getAllPrices() {
        return pricesRepository.findAll();
    }

    @Override
    public Prices getPrice(Long id) {
        return pricesRepository.getById(id);
    }
}
