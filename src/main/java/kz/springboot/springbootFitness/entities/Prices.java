package kz.springboot.springbootFitness.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Prices{" +
                "id=" + id +
                ", subscription=" + subscription +
                ", freezing=" + freezing +
                ", price=" + price +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "jyms_id")
    private Jyms jym;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "freezing_id")
    private Freezing freezing;

    private int price;

}
