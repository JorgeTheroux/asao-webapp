package dgtic.core.service.jpa;

import dgtic.core.entity.Plagas;
import dgtic.core.repository.PlagasRepository;
import dgtic.core.service.IPlagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlagasService implements IPlagasService {
    @Autowired
    PlagasRepository plagasRepository;
    @Override
    public Page<Plagas> findAll(Pageable pageable) {
        return plagasRepository.findAll(pageable);
    }

    @Override
    public void guardar(Plagas plagas) {
        plagasRepository.save(plagas);
    }

    @Override
    public void borrarPlagas(Integer id) {
        plagasRepository.deleteById(id);

    }

    @Override
    public Plagas buscarPlagas(Integer id) {
        Optional<Plagas> plagasOptional = plagasRepository.findById(id);
        return plagasOptional.get();
    }

    @Override
    public List<Plagas> buscarPlagas() {
        return plagasRepository.findAll();
    }
}
