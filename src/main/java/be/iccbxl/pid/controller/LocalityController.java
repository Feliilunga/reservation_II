package be.iccbxl.pid.controller;

import be.iccbxl.pid.model.Artist;
import be.iccbxl.pid.model.Locality;
import be.iccbxl.pid.model.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller

public class LocalityController {
    @Autowired
    LocalityService service;

    @GetMapping("/localities")

    public String index2(Model model){
        List<Locality> localities = service.getAllLocality();

        model.addAttribute("localities",localities);
        model.addAttribute("title","Liste de localite");

        return "locality/index";
    }

    @GetMapping("/localities/{id}")
    public String show(Model model, @PathVariable("id") String id)
    {
        Locality locality = service.get(id);

        model.addAttribute("locality",locality);
        model.addAttribute("title","Fiche d'une localite");

        return "locality/show";

    }
    @GetMapping("/localities/add")
    public String localityFormAdd(Model model)
    {
        model.addAttribute(new Locality());
        return "locality/addLocality";
    }
    @PostMapping("/localities/add")
    public String localitySubmitAdd(@Valid @ModelAttribute("locality") Locality locality, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            return "locality/addLocality";
        }
        locality.setPostalCode(locality.getPostalCode());
        locality.setLocalityName(locality.getLocalityName());

        model.addAttribute(new Locality());
        service.add(locality);

        return "redirect:/localities";
    }
    @GetMapping("localities/edit/{id}")
    public String localityFormEdit(Model model, @PathVariable("id") String id)
    {

        Locality locality = service.get(id);

        model.addAttribute("locality",locality);
        model.addAttribute("title","Fiche de une localite");

        return "locality/editLocality";

    }
    @PostMapping("localities/edit/{id}")
    public String localitySubmitEdit(@Valid @ModelAttribute("locality") Locality locality,BindingResult result,@PathVariable("id") String id,Model model)
    {
        if (result.hasErrors()) {
            return "locality/editLocality";
        }
        Locality existing = service.get(id);

        if(existing==null) {
            return "locality/index";
        }

        Long indice = (long) Integer.parseInt(id);

        locality.setId(indice);
        service.update(locality.getId(), locality);

        model.addAttribute("locality", locality);

        return "redirect:/localities/"+locality.getId();

    }
    @DeleteMapping("localities/delete/{id}")

    public String delete(@PathVariable("id") String id, Model model)
    {
        Locality existing = service.get(id);
        if(existing!=null) {
            Long indice = (long) Integer.parseInt(id);
            service.delete(indice);
        }
        return "redirect:/localities";
    }
}



