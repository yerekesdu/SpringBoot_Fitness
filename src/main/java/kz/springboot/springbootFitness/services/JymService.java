package kz.springboot.springbootFitness.services;

import kz.springboot.springbootFitness.entities.Jyms;
import kz.springboot.springbootFitness.entities.Prices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface JymService {

    Jyms addJym(Jyms jym);
    List<Jyms> getAllJyms();
    Jyms getJym(Long id);
    void deleteJym(Jyms jym);
    Jyms saveJym(Jyms jym);
    List<Jyms> getWithPrice();

}
