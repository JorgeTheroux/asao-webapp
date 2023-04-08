package dgtic.core.controller;
import dgtic.core.entity.Plagas;
import dgtic.core.entity.ReportesTecnicos;
import dgtic.core.model.HallazgosPlagas;
import dgtic.core.service.IPlagasService;
import dgtic.core.service.IReportesTecnicosService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("lista")
@RequestMapping(value = "hallazgos_plagas")
public class HallazgosPlagasController {

    @Autowired
    IReportesTecnicosService iReportesTecnicosService;

    @Autowired
    IPlagasService iPlagasService;

    //    @GetMapping(value = "agregar")
//    public String desplegar(Model model) {
//        EstudianteMateria estMateria = new EstudianteMateria();
//        List<EstudianteMateria> lista = new LinkedList<>();
//        model.addAttribute("estmateria", estMateria);
//        model.addAttribute("lista", lista);
//        return "estudiante_materia/est_mat";
//    }
    @GetMapping(value = "agregar")
    public String desplegarSelect(Model model) {
        HallazgosPlagas hallazgosPlagas = new HallazgosPlagas();
        List<HallazgosPlagas> lista = new LinkedList<>();
        model.addAttribute("hallazgosplagas", hallazgosPlagas);
        model.addAttribute("lista", lista);
        //datos select
        List<ReportesTecnicos> select = iReportesTecnicosService.buscarReportesTecnicos();
        model.addAttribute("selectreportestecnicos", select);

        List<Plagas> selectM = iPlagasService.buscarPlagas();
        model.addAttribute("selectplagas", selectM);

        return "hallazgos_plagas/hallazgosplagas_select";
    }

    @PostMapping(value = "/forminsertarselect")
    public String formClientesInsertarS(@Valid @ModelAttribute("hallazgosplagas") HallazgosPlagas hallazgosPlagas,
                                        BindingResult resultado, Model modelo,
                                        SessionStatus status, HttpSession sesion) {
        ReportesTecnicos e = iReportesTecnicosService.buscarReporteTecnico(hallazgosPlagas.getId_reporte());
        Plagas m = iPlagasService.buscarPlagas(hallazgosPlagas.getId_plaga());
        hallazgosPlagas.setId_reporte(e.getId_reporte());
        hallazgosPlagas.setNombre_plagas(m.getPlaga());
        System.out.println(hallazgosPlagas.toString());
        if (resultado.hasErrors()) {
            modelo.addAttribute("titulo", "Error al guardar");
            return "hallazgos_plagas/hallazgosplagas_select";
        }

        List<HallazgosPlagas> lista = (List<HallazgosPlagas>) sesion.getAttribute("lista");
        lista.add(hallazgosPlagas);

        return "hallazgos_plagas/hallazgosplagas_select";

    }

    @GetMapping(value = "/formguardar")
    public String formguardar(Model modelo, SessionStatus status, HttpSession sesion,
                              RedirectAttributes flash) {
        String salto = "redirect:/hallazgos_plagas/agregar";
        List<HallazgosPlagas> lista = (List<HallazgosPlagas>) sesion.getAttribute("lista");
        for (HallazgosPlagas datos : lista) {
            ReportesTecnicos reportesTecnicos = iReportesTecnicosService.buscarReporteTecnico(datos.getId_reporte());
            Plagas plg = new Plagas();
            plg.setId_plaga(datos.getId_plaga());
            reportesTecnicos.agregarPlagas(plg);
            try {
                iReportesTecnicosService.guardar(reportesTecnicos);
                flash.addFlashAttribute("mensaje", "Se almaceno con éxito");
            } catch (DataIntegrityViolationException ex) {
                HallazgosPlagas hallazgosPlagas = new HallazgosPlagas();
                modelo.addAttribute("hallazgosplagas", hallazgosPlagas);
                modelo.addAttribute("mensaje", "Reporte con la misma plaga (" + reportesTecnicos.getId_reporte() + ")");
                salto = "hallazgos_plagas/hallazgosplagas_select";
                break;
            }
        }
        return salto;
    }

    @GetMapping(value = "listas")
    public String lista(@RequestParam(name = "page", defaultValue = "0") int page,
                        Model modelo, SessionStatus status) {
        Pageable pagReq = PageRequest.of(page, 2);
        Page<ReportesTecnicos> est = iReportesTecnicosService.findAll(pagReq);
        RenderPagina<ReportesTecnicos> render = new RenderPagina<>("listas", est);
        modelo.addAttribute("listas", est);
        modelo.addAttribute("page", render);
        status.setComplete();
        return "hallazgosplagas/listahallazgosplagas";
    }



    @GetMapping("borrar/{id}")
    public String borrarReporte(@PathVariable("id") Integer id, Model modelo,HttpSession sesion) {
        List<HallazgosPlagas> lista = (List<HallazgosPlagas>) sesion.getAttribute("lista");
        for(HallazgosPlagas dato:lista){
            if(dato.getId_reporte().equals(id)){
                lista.remove(dato);
                break;
            }
        }
        HallazgosPlagas hallazgosPlagas = new HallazgosPlagas();
        modelo.addAttribute("hallazgosplagas", hallazgosPlagas);
        modelo.addAttribute("mensaje","Reporte se borro con éxito");
        //datos select
        List<ReportesTecnicos> select = iReportesTecnicosService.buscarReportesTecnicos();
        modelo.addAttribute("selectreporte", select);

        List<Plagas> selectM = iPlagasService.buscarPlagas();
        modelo.addAttribute("selectplaga", selectM);
        return "hallazgosplagas/hallazgosplagas_select";
    }



}
