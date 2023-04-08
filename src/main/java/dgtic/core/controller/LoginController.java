package dgtic.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "logout",required = false)String logout,
                        Model model, Principal principal, RedirectAttributes redirectAttributes){
        if(principal!=null){
            redirectAttributes.addFlashAttribute("mensaje","Ya tienes una sesión");
            return "redirect:/";
        }
        if(error!=null){
            model.addAttribute("error","Usuario o password incorrecto");

        }
        if(logout!=null){
            redirectAttributes.addFlashAttribute("mensaje","Sesión cerrada");
            return "redirect:/";
        }
        return "login";
    }
}
