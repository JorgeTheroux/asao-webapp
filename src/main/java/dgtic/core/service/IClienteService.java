package dgtic.core.service;

import dgtic.core.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public Page<Cliente> findAll(Pageable pageable);
    public void guardar(Cliente cliente);
    public void borrarCliente(Integer id);
    public Cliente buscarCliente(Integer id);
    List<Cliente> buscarClientes();
}
