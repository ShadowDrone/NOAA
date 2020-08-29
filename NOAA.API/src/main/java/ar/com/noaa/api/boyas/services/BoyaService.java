package ar.com.noaa.api.boyas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.repos.BoyaRepo;
import java.util.Optional;

@Service
public class BoyaService {
    @Autowired
    BoyaRepo boyaRepo;

    public List<Boya> obtenerBoyas() {
        return boyaRepo.findAll();
    }

    public void crearBoya(Boya boya) {
        boyaRepo.save(boya);
    }

    public void actualizarBoya(Boya boya) {
        boyaRepo.save(boya);
    }

    public Boya buscarBoyaPorId(Integer id) {
        Optional<Boya> boya = boyaRepo.findById(id);
        if (boya.isPresent())
            return boya.get();
        return null;

    }

}