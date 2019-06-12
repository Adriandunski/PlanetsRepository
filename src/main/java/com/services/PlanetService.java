package com.services;

import com.models.Planet;
import com.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet getPlanetByName(String planetName) {
        return Optional.ofNullable(planetRepository.findPlanetByPlanetName(planetName)).orElse(null);
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public List<Planet> getPlanets(String param) {
        return planetRepository.findPlanetByParam(param);
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public Planet updatePlanet(String planetName, Planet planet) {

        return Optional
                .ofNullable(planetRepository.findPlanetByPlanetName(planetName))
                .map(p -> updatePlanetResult(planet))
                .orElse(null);
    }

    public boolean deletePlanetByName(String planetName) {

        if (planetRepository.deletePlanetByName(planetName) == 1) {
            return true;
        }
       return false;
    }

    // ------- Helpers -------

    private Planet updatePlanetResult(Planet p) {
        return Planet
                .builder()
                .id(p.getId())
                .planetName(p.getPlanetName())
                .planetType(p.getPlanetType())
                .planetInfo(p.getPlanetInfo())
                .planetImage(p.getPlanetImage())
                .oneWayLightTimeToTheSun(p.getOneWayLightTimeToTheSun())
                .lengthOfYear(p.getLengthOfYear())
                .distanceFromSun(p.getDistanceFromSun())
                .build();
    }
}
