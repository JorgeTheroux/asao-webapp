package dgtic.core.model;

public class HallazgosPlagas {

    private Integer id_reporte;

    private Integer id_plaga;

    private String nombre_plagas;

    public HallazgosPlagas() {
    }

    public Integer getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Integer id_reporte) {
        this.id_reporte = id_reporte;
    }

    public Integer getId_plaga() {
        return id_plaga;
    }

    public void setId_plaga(Integer id_plaga) {
        this.id_plaga = id_plaga;
    }

    public String getNombre_plagas() {
        return nombre_plagas;
    }

    public void setNombre_plagas(String nombre_plagas) {
        this.nombre_plagas = nombre_plagas;
    }

    @Override
    public String toString() {
        return "HallazgosPlagas{" +
                "id_reporte=" + id_reporte +
                ", id_plaga=" + id_plaga +
                ", nombre_plagas='" + nombre_plagas + '\'' +
                '}';
    }
}
