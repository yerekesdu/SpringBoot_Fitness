package kz.springboot.springbootFitness.entities;

import kz.springboot.springbootFitness.services.JymService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MainListTest {
    
    @Autowired
    private JymService jymService;

    List<Jyms> jyms = jymService.getAllJyms();

    List<List<Prices>> prices = new ArrayList<List<Prices>>();



}
