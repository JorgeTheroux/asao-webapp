package dgtic.core.controller;

import dgtic.core.entity.Cliente;
import dgtic.core.entity.Plagas;
import dgtic.core.service.IClienteService;
import dgtic.core.service.IPlagasService;
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
@RequestMapping(value = "plagas")
public class PlagasController {
    @Autowired
    private IPlagasService iPlagasService;
    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue="0")int page,
                         Model model){
        Pageable pagReq= PageRequest.of(page,2);
        Page<Plagas> plg=iPlagasService.findAll(pagReq);
        RenderPagina<Plagas> render=new RenderPagina<>("lista",plg);
        model.addAttribute("plagas",plg);
        model.addAttribute("page",render);
        return "plagas/listaplagas";
    }

    @GetMapping ("formularioplagas")
    public String formularioPlagas(Model model){

        Plagas plagas =new Plagas();
        model.addAttribute("titulo","Nuevas Plagas");
        model.addAttribute("plagas",plagas);
        return "plagas/nuevasplagas";

    }


    @PostMapping(value="/forminsertar")
    public String formPlagasInsertar(@Valid @ModelAttribute("plagas")Plagas plagas,
                                         BindingResult resultado, Model modelo, RedirectAttributes flash){
        if(resultado.hasErrors()){
            modelo.addAttribute("titulo","Error al crear plagas");
            return "plagas/nuevasplagas";
        }
        iPlagasService.guardar(plagas);
        flash.addFlashAttribute("mensaje","Plaga se almaceno o modifico correctamente");
        return "redirect:/plagas/lista";
    }

    @GetMapping("modificarplagas/{id}")
    public String formularioPlagas(@PathVariable("id")Integer id, Model modelo){

        Plagas plagas=iPlagasService.buscarPlagas(id);
        modelo.addAttribute("titulo","Modificar plagas");
        modelo.addAttribute("plagas",plagas);
        return "plagas/modificarplagas";
    }

    @GetMapping("borrarplagas/{id}")
    public String borrarPlaga(@PathVariable("id")Integer id, Model model,RedirectAttributes flash){
        iPlagasService.borrarPlagas(id);
        flash.addFlashAttribute("mensaje", "Plaga se borro con exito");
        return "redirect:/plagas/lista";
    }




    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
