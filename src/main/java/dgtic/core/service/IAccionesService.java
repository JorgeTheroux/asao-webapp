package dgtic.core.service;

import dgtic.core.entity.Acciones;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccionesService {

    public Page<Acciones> findAll(Pageable pageable);
    public void guardar(Acciones acciones);
    public void borrar(Integer id);
    public Acciones buscarAcciones(Integer id);
    List<Acciones> buscarAcciones();


}
