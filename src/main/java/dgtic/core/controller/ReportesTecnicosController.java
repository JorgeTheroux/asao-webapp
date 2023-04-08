package dgtic.core.controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.entity.Cliente;
import dgtic.core.entity.ReportesTecnicos;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IReportesTecnicosService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "reportes_tecnicos")
public class ReportesTecnicosController {
    @Autowired
    private IReportesTecnicosService iReportesTecnicosService;
    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue="0")int page,
                         Model model){
        Pageable pagReq= PageRequest.of(page,2);
        Page<ReportesTecnicos> rpt=iReportesTecnicosService.findAll(pagReq);
        RenderPagina<ReportesTecnicos> render=new RenderPagina<>("lista",rpt);
        model.addAttribute("reportestecnicos",rpt);
        model.addAttribute("page",render);
        return "reportestecnicos/listareportestecnicos";
    }

    @GetMapping ("formularioreportestecnicos")
    public String formularioReportesTecnicos(Model model){

        ReportesTecnicos reportesTecnicos =new ReportesTecnicos();
        model.addAttribute("titulo","Nuevo ReporteTecnico");
        model.addAttribute("reportetecnico",reportesTecnicos);
        return "reportestecnicos/nuevosreportestecnicos";

    }


    @PostMapping(value="/forminsertar")
    public String formReportesInsertar(@Valid @ModelAttribute("reportetecnico")ReportesTecnicos reportesTecnicos,
                                         BindingResult resultado, Model modelo, RedirectAttributes flash){
        if(resultado.hasErrors()){
            modelo.addAttribute("titulo","Error al crear Reporte Tecnico");
            return "reportestecnicos/nuevosreportestecnicos";
        }
        iReportesTecnicosService.guardar(reportesTecnicos);
        flash.addFlashAttribute("mensaje","ReporteTecnico se almaceno o modifico correctamente");
        return "redirect:/reportes_tecnicos/lista";
    }

    @GetMapping("modificarreportestecnicos/{id}")
    public String formularioReporteTecnico(@PathVariable("id")Integer id, Model modelo){

        ReportesTecnicos reportesTecnicos=iReportesTecnicosService.buscarReporteTecnico(id);
        modelo.addAttribute("titulo","Modificar reporte tecnico");
        modelo.addAttribute("reportetecnico",reportesTecnicos);
        return "reportestecnicos/modificarreportestecnicos";
    }

    @GetMapping("borrarreportestecnicos/{id}")
    public String borrarCliente(@PathVariable("id")Integer id, Model model,RedirectAttributes flash){
        iReportesTecnicosService.borrar(id);
        flash.addFlashAttribute("mensaje", "Reporte Tecnico se borro con exito");
        return "redirect:/reportestecnicos/lista";
    }

    @GetMapping("pdf")
    public String Prodpdf(Model model){
        List<ReportesTecnicos> datos=iReportesTecnicosService.buscarReportesTecnicos();
        model.addAttribute("todos",datos);
        return "reportes_tecnicos/pdf";
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generarPDF(@PathVariable("id") Integer id) throws DocumentException {

        ReportesTecnicos reporte = iReportesTecnicosService.buscarReporteTecnico(id);
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
        Paragraph paragraph = new Paragraph("Reporte técnico");
        document.add(paragraph);
        paragraph = new Paragraph("Cliente: " + reporte.getCliente().getNombre_fiscal());
        document.add(paragraph);
        paragraph = new Paragraph("Servicio: " + reporte.getDatosServicio().getId_datos_servicio());
        document.add(paragraph);
        paragraph = new Paragraph("Acciones: " + reporte.getAcciones().getInsectida());
        document.add(paragraph);
        document.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }




    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
