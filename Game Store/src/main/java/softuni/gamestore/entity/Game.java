package softuni.gamestore.entity;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    private int id;
    private String name;
    private String dlc;
    private double honorarium;
    private String genre;

    public Game() {
    }

    public Game(String name, String dlc, double honorarium, String genre) {
        this.name = name;
        this.dlc = dlc;
        this.honorarium = honorarium;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDlc() {
        return dlc;
    }

    public void setDlc(String dlc) {
        this.dlc = dlc;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
