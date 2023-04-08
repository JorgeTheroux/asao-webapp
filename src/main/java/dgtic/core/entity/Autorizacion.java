package dgtic.core.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "autorizaciones")
public class Autorizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String usuario_usa;

    @NotNull
    private String rol;

    @OneToOne
    @MapsId
    @JoinColumn(name="usuario_usa")
    private Usuario usuario;

    public String getUsuario_usa() {
        return usuario_usa;
    }

    public void setUsuario_usa(String usuario_usa) {
        this.usuario_usa = usuario_usa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Autorizacion{" +
                "usuario_usa='" + usuario_usa + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
