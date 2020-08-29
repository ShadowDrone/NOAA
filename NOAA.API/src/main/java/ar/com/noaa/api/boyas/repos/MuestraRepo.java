package ar.com.noaa.api.boyas.repos;

import org.springframework.stereotype.Repository;

import ar.com.noaa.api.boyas.entities.Muestra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MuestraRepo extends JpaRepository<Muestra, Integer> {
    
    @Query("select m from Muestra m where m.boya.id=:boyaId and m.alturaMar=(select min(m.alturaMar) from m where m.boya.id=:boyaId)")
    Muestra findByAlturaMin(Integer boyaId);
}