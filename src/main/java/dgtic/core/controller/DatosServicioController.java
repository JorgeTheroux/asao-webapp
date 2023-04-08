package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.DatosServicio;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IDatosServicioService;
import dgtic.core.utilerias.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "datosservicio")
public class DatosServicioController {
    @Autowired
    private IDatosServicioService iDatosServicioService;
    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue="0")int page,
                         Model model){
        Pageable pagReq= PageRequest.of(page,2);
        Page<DatosServicio> dts=iDatosServicioService.findAll(pagReq);
        RenderPagina<DatosServicio> render=new RenderPagina<>("lista",dts);
        model.addAttribute("datosservicio",dts);
        model.addAttribute("page",render);
        return "datosservicio/listadatosservicio";
    }

    @GetMapping ("formulariodatosservicio")
    public String formularioDatosServicio(Model model){

        DatosServicio datosServicio =new DatosServicio();
        model.addAttribute("titulo","Nuevo DatosServicio");
        model.addAttribute("datosservicio",datosServicio);
        return "datosservicio/nuevosdatosservicio";

    }


    @PostMapping(value="/forminsertar")
    public String formDatosServicioInsertar(@Valid @ModelAttribute("datosservicio")DatosServicio datosServicio,
                                         BindingResult resultado, Model modelo, RedirectAttributes flash){
        if(resultado.hasErrors()){
            modelo.addAttribute("titulo","Error al crear el Dato Servicio");
            return "datosservicio/nuevosdatosservicio";
        }
        iDatosServicioService.guardar(datosServicio);
        flash.addFlashAttribute("mensaje","DatoServicio se almaceno o modifico correctamente");
        return "redirect:/datosservicio/lista";
    }

    @GetMapping("modificardatosservicio/{id}")
    public String formularioDatosServicio(@PathVariable("id")Integer id, Model modelo){

        DatosServicio datosServicio=iDatosServicioService.buscarServicio(id);
        modelo.addAttribute("titulo","Modificar datosservicio");
        modelo.addAttribute("datosservicio",datosServicio);
        return "datosservicio/modificardatosservicio";
    }

    @GetMapping("borrardatosservicio/{id}")
    public String borrarDatosServicio(@PathVariable("id")Integer id, Model model,RedirectAttributes flash){
        iDatosServicioService.borrar(id);
        flash.addFlashAttribute("mensaje", "Dato del servicio se borro con exito");
        return "redirect:/datosservicio/lista";
    }




    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
