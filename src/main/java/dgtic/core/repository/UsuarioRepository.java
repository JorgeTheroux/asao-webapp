package dgtic.core.repository;

import dgtic.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    @Query(value = "select a from Usuario a "+
            "where a.usuario_usa=?1")
    public Usuario consultaId(String usuario);
}
