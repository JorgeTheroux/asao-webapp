package dgtic.core.service.jpa;

import dgtic.core.entity.DatosServicio;
import dgtic.core.repository.DatosServicioRepository;
import dgtic.core.service.IDatosServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatosServicioService implements IDatosServicioService {
    @Autowired
    DatosServicioRepository datosServicioRepository;
    @Override
    public Page<DatosServicio> findAll(Pageable pageable) {
        return datosServicioRepository.findAll(pageable);
    }

    @Override
    public void guardar(DatosServicio datosServicio) {
        datosServicioRepository.save(datosServicio);

    }

    @Override
    public void borrar(Integer id) {
        datosServicioRepository.deleteById(id);

    }

    @Override
    public DatosServicio buscarServicio(Integer id) {
        Optional<DatosServicio> datosServicioOptional = datosServicioRepository.findById(id);
        return datosServicioOptional.get();
    }

    @Override
    public List<DatosServicio> buscarDatosServicio() {
        return datosServicioRepository.findAll();
    }
}
