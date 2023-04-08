package dgtic.core.controller;

import dgtic.core.entity.Acciones;
import dgtic.core.entity.Cliente;
import dgtic.core.service.IAccionesService;
import dgtic.core.service.IClienteService;
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
@RequestMapping(value = "acciones")
public class AccionesController {
    @Autowired
    private IAccionesService iAccionesService;
    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue="0")int page,
                         Model model){
        Pageable pagReq= PageRequest.of(page,2);
        Page<Acciones> acc=iAccionesService.findAll(pagReq);
        RenderPagina<Acciones> render=new RenderPagina<>("lista",acc);
        model.addAttribute("acciones",acc);
        model.addAttribute("page",render);
        return "acciones/listaacciones";
    }

    @GetMapping ("formularioacciones")
    public String formularioAcciones(Model model){

        Acciones acciones =new Acciones();
        model.addAttribute("titulo","Nuevas Acciones");
        model.addAttribute("acciones",acciones);
        return "acciones/nuevasacciones";

    }


    @PostMapping(value="/forminsertar")
    public String formAccionesInsertar(@Valid @ModelAttribute("acciones")Acciones acciones,
                                         BindingResult resultado, Model modelo, RedirectAttributes flash){
        if(resultado.hasErrors()){
            modelo.addAttribute("titulo","Error al crear la accion");
            return "acciones/nuevasacciones";
        }
        iAccionesService.guardar(acciones);
        flash.addFlashAttribute("mensaje","Accion se almaceno o modifico correctamente");
        return "redirect:/acciones/lista";
    }

    @GetMapping("modificaracciones/{id}")
    public String formularioAcciones(@PathVariable("id")Integer id, Model modelo){

        Acciones acciones=iAccionesService.buscarAcciones(id);
        modelo.addAttribute("titulo","Modificar acciones");
        modelo.addAttribute("acciones",acciones);
        return "acciones/modificaracciones";
    }

    @GetMapping("borraracciones/{id}")
    public String borrarAcciones(@PathVariable("id")Integer id, Model model,RedirectAttributes flash){
        iAccionesService.borrar(id);
        flash.addFlashAttribute("mensaje", "Accion se borro con exito");
        return "redirect:/acciones/lista";
    }




    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
