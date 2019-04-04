package softuni.gamestore.gameBindingModel;

public class GameBindingModel {

    private String name;
    private String dlc;
    private double honorarium;
    private String genre;

    public GameBindingModel() {
    }

    public GameBindingModel(String name, String dlc, double honorarium, String genre) {
        this.name = name;
        this.dlc = dlc;
        this.honorarium = honorarium;
        this.genre = genre;
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
