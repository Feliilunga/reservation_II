package be.iccbxl.pid.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import be.iccbxl.pid.model.ArtistService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import be.iccbxl.pid.model.Type;
import be.iccbxl.pid.model.TypeService;

@Controller
public class TypeController {
    @Autowired
    TypeService service;

    @Autowired
    ArtistService artistService;

    @GetMapping("/types")


    public String index2(Model model) {
        List<Type> types = service.getAllType();
        model.addAttribute("types", types);
        model.addAttribute("title", "Liste des types");

        return "type/index";
    }

    @GetMapping("/types/{id}")

    public String show(Model model, @PathVariable("id") String id) {
        Type type = service.get(id);

        model.addAttribute("type", type);
        model.addAttribute("title", "Fiche d'un type");

        return "type/show";
    }


    @GetMapping("/types/create")

    public String typeFormAdd(Model model) {


        model.addAttribute(new Type());

        return "type/create";
    }

    @PostMapping("/types/create")
    public String typeAddSubmit(@Valid @ModelAttribute("type") Type type, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "type/create";
        }


        model.addAttribute(new Type());
        service.add(type);
        return "redirect:/types";

    }

    @GetMapping("/types/edit/{id}")

    public String typeEditForm(Model model, @PathVariable("id") String id) {


        Type type = service.get(id);

        model.addAttribute("type", type);
        model.addAttribute("title", "Liste des types");


        return "type/edit";
    }

    @PostMapping("/types/edit/{id}")
    public String typeEditSubmit(@Valid @ModelAttribute("type") Type type, BindingResult result, @PathVariable("id") String id, Model model) {


        if (result.hasErrors()) {
            return "type/edit";
        }

        Type existing = service.get(id);

        if (existing == null) {
            return "type/index";
        }


        Long indice = (long) Integer.parseInt(id);

        type.setId(indice);
        service.update(type.getId(), type); //perche update ci chiede due parametri id e object,x quello faccio get

        model.addAttribute("type", type);

        return "redirect:/types/" + type.getId();



       /* if (result.hasErrors()) {
            return "type/editType";
        }

        service.add(type);



        return "type/show";

        */

    }

    @DeleteMapping("types/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        Type existing = service.get(id);
        if (existing != null) {
            Long indice = (long) Integer.parseInt(id);
            service.delete(indice);
        }
        return "redirect:/types";

    }

}