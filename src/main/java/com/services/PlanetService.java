package com.services;

import com.commons.mappers.PlanetMapper;
import com.models.Planet;
import com.models.PlanetDto;
import com.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    private PlanetMapper planetMapper;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    public Planet getPlanetByName(String planetName) {
        return Optional.ofNullable(planetRepository.findPlanetByPlanetName(planetName)).orElse(null);
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public List<PlanetDto> getPlanetsDto() {
        return planetRepository.findAll()
                .stream()
                .map(planetMapper::map)
                .collect(Collectors.toList());
    }

    public List<Planet> getPlanets(String param) {
        return planetRepository.findPlanetByParam(param);
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public List<Planet> getPlanetsLengthFromSun(long distance) {

        List<Planet> planets = planetRepository.findAll();
        return planets.stream().filter(p -> p.getDistanceFromSun() < distance).collect(Collectors.toList());
    }

    public Planet updatePlanet(String planetName, Planet planet) {
        return Optional
                .ofNullable(planetRepository.findPlanetByPlanetName(planetName))
                .map(p -> {
                    p.setPlanetName(planet.getPlanetName());
                    p.setPlanetType(planet.getPlanetType());
                    p.setPlanetInfo(planet.getPlanetInfo());
                    p.setPlanetImage(planet.getPlanetImage());
                    p.setOneWayLightTimeToTheSun(planet.getOneWayLightTimeToTheSun());
                    p.setLengthOfYear(planet.getLengthOfYear());
                    p.setDistanceFromSun(planet.getDistanceFromSun());
                    return planetRepository.save(p);
                })
                .orElse(null);
    }

//    public void updatePlanetVoid(String planetName, Planet planet) {
//        Optional.ofNullable(planetRepository.findPlanetByPlanetName(planetName))
//                .ifPresent(p -> {
//                    p.setPlanetName(planet.getPlanetName());
//                    p.setPlanetType(planet.getPlanetType());
//                    p.setPlanetInfo(planet.getPlanetInfo());
//                    p.setPlanetImage(planet.getPlanetImage());
//                    p.setOneWayLightTimeToTheSun(planet.getOneWayLightTimeToTheSun());
//                    p.setLengthOfYear(planet.getLengthOfYear());
//                    p.setDistanceFromSun(planet.getDistanceFromSun());
//                    planetRepository.save(p);
//                });
//    }


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
