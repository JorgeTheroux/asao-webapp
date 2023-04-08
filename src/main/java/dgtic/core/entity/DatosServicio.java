package dgtic.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "datos_del_servicio")
public class DatosServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_datos_servicio;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    private String lugar;
    private Date fecha;
    private String tipo_de_servicio;

    public DatosServicio() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId_datos_servicio() {
        return id_datos_servicio;
    }

    public void setId_datos_servicio(Integer id_datos_servicio) {
        this.id_datos_servicio = id_datos_servicio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo_de_servicio() {
        return tipo_de_servicio;
    }

    public void setTipo_de_servicio(String tipo_de_servicio) {
        this.tipo_de_servicio = tipo_de_servicio;
    }

    @Override
    public String toString() {
        return "DatosServicio{" +
                "id_datos_servicio=" + id_datos_servicio +
                ", cliente=" + cliente +
                ", lugar='" + lugar + '\'' +
                ", fecha=" + fecha +
                ", tipo_de_servicio='" + tipo_de_servicio + '\'' +
                '}';
    }
}
