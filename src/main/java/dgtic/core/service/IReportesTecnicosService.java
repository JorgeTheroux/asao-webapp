package dgtic.core.service;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.ReportesTecnicos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReportesTecnicosService {

    public Page<ReportesTecnicos> findAll(Pageable pageable);
    public void guardar(ReportesTecnicos reportesTecnicos);
    public void borrar(Integer id);
    public ReportesTecnicos buscarReporteTecnico(Integer id);
    List<ReportesTecnicos> buscarReportesTecnicos();
}
