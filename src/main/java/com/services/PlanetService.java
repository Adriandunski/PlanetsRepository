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
        Optional<Planet> result = planetRepository.findPlanetByPlanetName(planetName);
        if (result.isPresent()) {
            return planetRepository.findPlanetByPlanetName(planetName).get();
        }
        return null;
    }

    public Optional<Planet> getOptionalPlanetByName(String planetName) {
        return Optional.of(planetRepository.findPlanetByPlanetName(planetName)).orElse(Optional.empty());
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
        Optional<Planet> result = planetRepository.findPlanetByPlanetName(planetName);

        if (result.isPresent()) {
            result.get().setPlanetName(planet.getPlanetName());
            result.get().setDistanceFromSun(planet.getDistanceFromSun());
            result.get().setLengthOfYear(planet.getLengthOfYear());
            result.get().setOneWayLightTimeToTheSun(planet.getOneWayLightTimeToTheSun());
            result.get().setPlanetInfo(planet.getPlanetInfo());
            result.get().setPlanetType(planet.getPlanetType());
            result.get().setPlanetImage(planet.getPlanetImage());

            return planetRepository.save(result.get());
        }
        return null;
    }

    public boolean deletePlanetByName(String planetName) {

        if (planetRepository.deletePlanetByName(planetName) == 1) {
            return true;
        }
       return false;
    }
}
