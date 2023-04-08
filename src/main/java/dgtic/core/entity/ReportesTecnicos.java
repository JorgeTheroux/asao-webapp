package dgtic.core.entity;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "reportes_tecnicos")
public class ReportesTecnicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reporte;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_datos_servicio")
    private DatosServicio datosServicio;

    @ManyToOne
    @JoinColumn(name = "id_accion")
    private Acciones acciones;

    //enlace de muchos a muchos
    //eager me trae todos lazy me tare uno en especifico
    @ManyToMany(fetch = FetchType.EAGER)
    //nombre de la tabala
    @JoinTable(name = "hallazgos_plagas",joinColumns = @JoinColumn(name = "id_reporte"),
            inverseJoinColumns = @JoinColumn(name = "id_plaga")
    )
    //es importante para el jointable
    private List<Plagas> plagas=new LinkedList<Plagas>();


    public ReportesTecnicos() {
    }

    public Integer getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Integer id_reporte) {
        this.id_reporte = id_reporte;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DatosServicio getDatosServicio() {
        return datosServicio;
    }

    public void setDatosServicio(DatosServicio datosServicio) {
        this.datosServicio = datosServicio;
    }

    public Acciones getAcciones() {
        return acciones;
    }

    public void setAcciones(Acciones acciones) {
        this.acciones = acciones;
    }

    public List<Plagas> getPlagas() {
        return plagas;
    }

    public void setPlagas(List<Plagas> plagas) {
        this.plagas = plagas;
    }

    public void agregarPlagas(Plagas plagas){
        this.plagas.add(plagas);

    }
}
