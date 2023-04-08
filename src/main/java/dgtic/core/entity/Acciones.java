package dgtic.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "acciones")
public class Acciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_accion;

    @Column(name = "insecticida_aplicado")
    private String insectida;

    public Acciones() {
    }

    public Integer getId_accion() {
        return id_accion;
    }

    public void setId_accion(Integer id_accion) {
        this.id_accion = id_accion;
    }

    public String getInsectida() {
        return insectida;
    }

    public void setInsectida(String insectida) {
        this.insectida = insectida;
    }

    @Override
    public String toString() {
        return "Acciones{" +
                "id_accion=" + id_accion +
                ", insectida='" + insectida + '\'' +
                '}';
    }
}
