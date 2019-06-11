package com.repositories;

import com.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    //@Query(value = "select * from planets where planet_name = ?1", nativeQuery = true) // SQL
    @Query("select p from Planet p where p.planetName = ?1") //JPQL
    Optional<Planet> findPlanetByPlanetName(String planetName);

    @Query("select p from Planet p where p.planetName like %?1% or p.planetInfo like %?1% or p.planetType like %?1%") //JPQL
    List<Planet> findPlanetByParam(String param);

    @Transactional //spring
    @Modifying
    @Query("delete from Planet p where p.planetName = ?1")
    int deletePlanetByName(String planetName);
}
