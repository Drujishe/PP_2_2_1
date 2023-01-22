package hiber.model;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @OneToOne
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this(model, series, null);
    }

    public Car(String model, int series, User user) {
        this.model = model;
        this.series = series;
        this.user = user;
        user.setCar(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
