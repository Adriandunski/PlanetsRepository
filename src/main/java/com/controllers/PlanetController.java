package com.controllers;

import com.models.Planet;
import com.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planet")
    public ResponseEntity<Planet> getPlanetByName(@RequestParam(value = "name") String planetName) {
        Planet result = planetService.getPlanetByName(planetName);

        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/planet/optional")
    public ResponseEntity<Optional<Planet>> getPlanetByNameOptional(@RequestParam(value = "name") String planetName) {
        return new ResponseEntity<>(planetService.getOptionalPlanetByName(planetName), HttpStatus.OK);
    }

    @GetMapping("planets")
    public List<Planet> getPlanets(@RequestParam(value = "param", required = false) String param) {
        if(param != null) {
            return planetService.getPlanets(param);
        } else {
            return planetService.getPlanets();
        }
    }

    @PostMapping("/planet")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) { //Przez JSON spodziewa sie jakiego ciala
        return ResponseEntity.ok()
                .header("example_header", "example_header_1")
                .body(planetService.savePlanet(planet));
    }

    @PutMapping("/planet")
    public ResponseEntity<Planet> updatePlanet(@RequestParam(value = "name") String planetName, @RequestBody Planet planet) {
        Planet result = planetService.getPlanetByName(planetName);

        if (result != null) {
            return ResponseEntity.ok()
                    .header("example_header", "example_header_1")
                    .body(planetService.savePlanet(result));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/planet")
    public ResponseEntity<?> deletePlanetByName(@RequestParam(value = "name") String planetName) {

        if (planetService.deletePlanetByName(planetName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
