package kz.springboot.springbootFitness;

import kz.springboot.springbootFitness.entities.AppUser;
import kz.springboot.springbootFitness.repositories.AppUserRepository;
import kz.springboot.springbootFitness.repositories.RoleRepository;
import kz.springboot.springbootFitness.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringbootFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFitnessApplication.class, args);

	}

}
