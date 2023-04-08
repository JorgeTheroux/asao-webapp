package dgtic.core.service;


import dgtic.core.entity.Plagas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlagasService {

    public Page<Plagas> findAll(Pageable pageable);
    public void guardar(Plagas plagas);
    public void borrarPlagas(Integer id);
    public Plagas buscarPlagas(Integer id);
    List<Plagas> buscarPlagas();
}
