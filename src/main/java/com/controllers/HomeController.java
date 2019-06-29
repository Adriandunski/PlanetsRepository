package com.controllers;

import com.commons.mappers.PlanetMapper;
import com.models.PlanetDto;
import com.services.PlanetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private PlanetService planetService;
    private PlanetMapper planetMapper;

    public HomeController(PlanetService planetService, PlanetMapper planetMapper) {
        this.planetService = planetService;
        this.planetMapper = planetMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'GUEST')")
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("planets", planetService.getPlanetsDto());
        return "index";
    }

    @GetMapping("/delete")
    public String deletePlanet(@RequestParam(value = "planet") String planetName) {
        planetService.deletePlanetByName(planetName);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePlanet(@RequestBody()PlanetDto planet) {
        // todo do zrobienie update planet
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addPlanet(@ModelAttribute()PlanetDto planetDto) {
        planetService.savePlanet(planetMapper.reverseMap(planetDto));
        return "redirect:/";
    }

    /** Służy do wywołania strony add-planet.html*/
    @GetMapping("/add-page")
    public String addPage(Model model) {
        return "addPlanet";
    }
}
