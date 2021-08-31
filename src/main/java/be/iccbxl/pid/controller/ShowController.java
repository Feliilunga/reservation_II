package be.iccbxl.pid.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import be.iccbxl.pid.model.ArtistType;
import be.iccbxl.pid.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.model.Show;
import be.iccbxl.pid.model.ShowService;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

    @GetMapping("/shows")
    public String index(Model model) {
        List<Show> shows = service.getAll();

        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.get(id);

        //Récupérer les artistes du spectacle et les grouper par type
        Map<Type,ArrayList<ArtistType>> collaborateurs = new TreeMap<>();

        for(ArtistType at : show.getArtistTypes()) {
            if(collaborateurs.get(at.getType()) == null) {
                collaborateurs.put(at.getType(), new ArrayList<>());
            }

            collaborateurs.get(at.getType()).add(at);
        }

        model.addAttribute("collaborateurs", collaborateurs);

        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");

        return "show/show";
    }

}

