package dgtic.core.service.jpa;



import dgtic.core.entity.ReportesTecnicos;
import dgtic.core.repository.ReportesTecnicosRepository;
import dgtic.core.service.IReportesTecnicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportesTecnicosService implements IReportesTecnicosService {
    @Autowired
    ReportesTecnicosRepository reportesTecnicosRepository;

    @Override
    public Page<ReportesTecnicos> findAll(Pageable pageable) {
        return reportesTecnicosRepository.findAll(pageable);
    }

    @Override
    public void guardar(ReportesTecnicos reportesTecnicos) {
        reportesTecnicosRepository.save(reportesTecnicos);

    }

    @Override
    public void borrar(Integer id) {
        reportesTecnicosRepository.deleteById(id);

    }

    @Override
    public ReportesTecnicos buscarReporteTecnico(Integer id) {
        Optional<ReportesTecnicos> reportesTecnicosOptional =  reportesTecnicosRepository.findById(id);
        return reportesTecnicosOptional.get();
    }

    @Override
    public List<ReportesTecnicos> buscarReportesTecnicos() {
        return reportesTecnicosRepository.findAll();
    }

}
