package dgtic.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String usuario_usa;

    @NotNull
    private String clave;

    @NotNull
    private Integer alta;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Autorizacion autorizacion;

    public String getUsuario_usa() {
        return usuario_usa;
    }

    public void setUsuario_usa(String usuario_usa) {
        this.usuario_usa = usuario_usa;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getAlta() {
        return alta;
    }

    public void setAlta(Integer alta) {
        this.alta = alta;
    }

    public Autorizacion getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_usa='" + usuario_usa + '\'' +
                ", clave='" + clave + '\'' +
                ", alta=" + alta +
                ", autorizacion=" + autorizacion +
                '}';
    }
}
