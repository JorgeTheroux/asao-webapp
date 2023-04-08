package dgtic.core.service.jpa;

import dgtic.core.entity.Cliente;
import dgtic.core.repository.ClienteRepository;
import dgtic.core.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);

    }

    @Override
    public void borrarCliente(Integer id) {
        clienteRepository.deleteById(id);

    }

    @Override
    public Cliente buscarCliente(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.get();
    }

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }
}
