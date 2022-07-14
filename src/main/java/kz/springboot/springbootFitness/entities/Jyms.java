package kz.springboot.springbootFitness.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jyms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jyms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contact_number;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(name = "gym_Pic")
    private String gymPic;

    @OneToMany(mappedBy = "jym")
    private List<Prices> prices;

//    @OneToMany(mappedBy = "jym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Prices> prices;


    @Override
    public String toString() {
        return "Jyms{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", description='" + description + '\'' +
                ", gymPic='" + gymPic + '\'' +
                '}';
    }
}
