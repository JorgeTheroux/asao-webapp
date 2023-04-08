package dgtic.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "plagas")
public class Plagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plaga;

    private String plaga;

    public Plagas() {
    }

    public Integer getId_plaga() {
        return id_plaga;
    }

    public void setId_plaga(Integer id_plaga) {
        this.id_plaga = id_plaga;
    }

    public String getPlaga() {
        return plaga;
    }

    public void setPlaga(String plaga) {
        this.plaga = plaga;
    }

    @Override
    public String toString() {
        return "plagas{" +
                "id_plaga=" + id_plaga +
                ", plaga='" + plaga + '\'' +
                '}';
    }
}
