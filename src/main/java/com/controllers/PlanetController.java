package com.controllers;

import com.commons.extras.CreatorXLS;
import com.commons.extras.DirectoryCreator;
import com.models.Planet;
import com.models.PlanetDto;
import com.services.PlanetService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
        DirectoryCreator.creatDirectory();
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

    @GetMapping("planets")
    public List<Planet> getPlanets(@RequestParam(value = "param", required = false) String param) {
        if(param != null) {
            return planetService.getPlanets(param);
        } else {
            return planetService.getPlanets();
        }
    }

    /** Generowanie pliku XLS*/
    @GetMapping("planets/file")
    public void getPlanetsInFile(@RequestParam String filename) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        planetService.getFile(filename);
    }

    @GetMapping("planets/dto")
    public List<PlanetDto> getPlanetsDto() {
        return planetService.getPlanetsDto();
    }

    @PostMapping("/planet")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) { //Przez JSON spodziewa sie jakiego ciala
        return ResponseEntity.ok()
                .header("example_header", "example_header_1")
                .body(planetService.savePlanet(planet));
    }

    @PutMapping("/planet")
    public ResponseEntity<Planet> updatePlanet(@RequestParam(value = "name") String planetName, @RequestBody Planet planet) {
        Planet result = planetService.updatePlanet(planetName, planet);

        if (result != null) {
            return ResponseEntity.ok()
                    .header("example_header", "example_header_1")
                    .body(result);
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

    @GetMapping("/planet/download/file/xls/{filename}")
    public ResponseEntity<Resource> downloadXls(@PathVariable String filename) throws IOException {
        Resource resource = new UrlResource(Paths.get("C:\\files\\" + filename).toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFile().getName() + "\"")
                .contentLength(resource.getFile().length())
                .body(resource);
    }
}
