package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.DatosServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDatosServicioService {

    public Page<DatosServicio> findAll(Pageable pageable);
    public void guardar(DatosServicio datosServicio);
    public void borrar(Integer id);
    public DatosServicio buscarServicio(Integer id);
    List<DatosServicio> buscarDatosServicio();
}
