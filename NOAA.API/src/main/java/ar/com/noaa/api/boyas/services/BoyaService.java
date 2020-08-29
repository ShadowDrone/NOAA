package ar.com.noaa.api.boyas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.repos.BoyaRepo;

@Service
public class BoyaService {
    @Autowired
    BoyaRepo boyaRepo;

    public List<Boya> obtenerBoyas() {
        return boyaRepo.findAll();
    }

    public void crearBoya (Boya boya){
        boyaRepo.save(boya);
    }

}