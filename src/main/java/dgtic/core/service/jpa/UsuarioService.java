package dgtic.core.service.jpa;

import dgtic.core.entity.Usuario;
import dgtic.core.repository.UsuarioRepository;
import dgtic.core.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarUsuario(String dato) {
        return usuarioRepository.consultaId(dato);
    }
}
