package kz.springboot.springbootFitness.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "trainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private int experience; // How many years

    @ManyToOne(fetch = FetchType.EAGER)
    private Jyms jym;

    @Column(name = "trainer_Pic")
    private String trainerPic;

    @Column(columnDefinition = "TEXT")
    private String info;

}
