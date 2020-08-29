package ar.com.noaa.api.boyas.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.noaa.api.boyas.entities.Boya;

@Repository
public interface BoyaRepo extends JpaRepository<Boya,Integer>{
    
}