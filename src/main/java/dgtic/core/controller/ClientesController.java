package dgtic.core.controller;

import dgtic.core.entity.Cliente;

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
@RequestMapping(value = "clientes")
public class ClientesController {
    @Autowired
    private IClienteService iClienteService;
    @GetMapping("lista")
    public String inicio(@RequestParam(name = "page", defaultValue="0")int page,
                         Model model){
        Pageable pagReq= PageRequest.of(page,2);
        Page<Cliente> clt=iClienteService.findAll(pagReq);
        RenderPagina<Cliente> render=new RenderPagina<>("lista",clt);
        model.addAttribute("clientes",clt);
        model.addAttribute("page",render);
        return "clientes/listaclientes";
    }

    @GetMapping ("formulariocliente")
    public String formularioCliente(Model model){

        Cliente estudiante =new Cliente();
        model.addAttribute("titulo","Nuevo Cliente");
        model.addAttribute("cliente",estudiante);
        return "clientes/nuevosclientes";

    }


    @PostMapping(value="/forminsertar")
    public String formClienteInsertar(@Valid @ModelAttribute("cliente")Cliente cliente,
                                         BindingResult resultado, Model modelo, RedirectAttributes flash){
        if(resultado.hasErrors()){
            modelo.addAttribute("titulo","Error al crear al cliente");
            return "clientes/nuevosclientes";
        }
        iClienteService.guardar(cliente);
        flash.addFlashAttribute("mensaje","Cliente se almaceno o modifico correctamente");
        return "redirect:/clientes/lista";
    }

    @GetMapping("modificarclientes/{id}")
    public String formularioCliente(@PathVariable("id")Integer id, Model modelo){

        Cliente cliente=iClienteService.buscarCliente(id);
        modelo.addAttribute("titulo","Modificar cliente");
        modelo.addAttribute("cliente",cliente);
        return "clientes/modificarclientes";
    }

    @GetMapping("borrarclientes/{id}")
    public String borrarCliente(@PathVariable("id")Integer id, Model model,RedirectAttributes flash){
        iClienteService.borrarCliente(id);
        flash.addFlashAttribute("mensaje", "Cliente se borro con exito");
        return "redirect:/clientes/lista";
    }




    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
    }



}
