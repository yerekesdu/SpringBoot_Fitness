package kz.springboot.springbootFitness.services.impl;

import kz.springboot.springbootFitness.entities.Jyms;
import kz.springboot.springbootFitness.entities.Prices;
import kz.springboot.springbootFitness.repositories.JymRepository;
import kz.springboot.springbootFitness.services.JymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JymsServiceImpl implements JymService {

    @Autowired
    private JymRepository jymRepository;

    @Override
    public List<Jyms> getWithPrice() {
        return jymRepository.findDistinctByPricesIsNotNull();
    }

    @Override
    public Jyms addJym(Jyms jym) {
        return jymRepository.save(jym);
    }

    @Override
    public List<Jyms> getAllJyms() {
        return jymRepository.findAll();
    }

    @Override
    public Jyms getJym(Long id) {
        return jymRepository.getById(id);
    }

    @Override
    public void deleteJym(Jyms jym) {
        jymRepository.delete(jym);
    }

    @Override
    public Jyms saveJym(Jyms jym) {
        return jymRepository.save(jym);
    }
}
