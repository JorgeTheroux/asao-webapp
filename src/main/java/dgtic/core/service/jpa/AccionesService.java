package dgtic.core.service.jpa;

import dgtic.core.entity.Acciones;
import dgtic.core.repository.AccionesRepository;
import dgtic.core.service.IAccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccionesService implements IAccionesService {

    @Autowired
    AccionesRepository accionesRepository;

    @Override
    public Page<Acciones> findAll(Pageable pageable) {
        return accionesRepository.findAll(pageable);
    }

    @Override
    public void guardar(Acciones acciones) {
        accionesRepository.save(acciones);

    }

    @Override
    public void borrar(Integer id) {
        accionesRepository.deleteById(id);

    }

    @Override
    public Acciones buscarAcciones(Integer id) {
        Optional<Acciones> accionesOptional = accionesRepository.findById(id);
        return accionesOptional.get();
    }


    @Override
    public List<Acciones> buscarAcciones() {
        return accionesRepository.findAll();
    }



}
