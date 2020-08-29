package ar.com.noaa.api.boyas.services;

import org.springframework.stereotype.Service;
import ar.com.noaa.api.boyas.entities.Boya;
import ar.com.noaa.api.boyas.entities.Muestra;
import ar.com.noaa.api.boyas.models.request.MuestraReq;
import ar.com.noaa.api.boyas.services.BoyaService;
import ar.com.noaa.api.boyas.repos.MuestraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class MuestraService {

    @Autowired
    BoyaService boyaService;

    @Autowired
    MuestraRepo muestraRepo;

    public Muestra crearMuestra(Integer boyaId, Date horarioMuestra, Double latitud, Double longitud, String matriculaEmbarcacion, Double altura) {
        Boya boya = boyaService.buscarBoyaPorId(boyaId);
        if (boya != null) {

            Muestra muestra = new Muestra();
            muestra.setAlturaMar(altura);
            muestra.setHorario(horarioMuestra);
            muestra.setLatitud(latitud);
            muestra.setLongitud(longitud);
            muestra.setMatricula(matriculaEmbarcacion);
            boya.agregarMuestra(muestra);

        
            //Este orden de IF esta mal a proposito para en el proximo commit corregirlo
            //usando unit tests
            if (muestra.getAlturaMar() > 50 || muestra.getAlturaMar() < -50) {
                boya.setColor("AMARILLO");
            }
            else if (muestra.getAlturaMar() < 100 || muestra.getAlturaMar() < -100) {
                boya.setColor("ROJO");
    
            } else {
                boya.setColor("VERDE");
            }

            grabarMuestra(muestra);
            return boya.getMuestras().get(boya.getMuestras().size()-1);
        } else {
            return null;
        }

    }

    public void grabarMuestra(Muestra muestra) {

        boyaService.actualizarBoya(muestra.getBoya());

    }

    public Muestra buscarPorId(Integer id) {
        Optional<Muestra> opMuestra = muestraRepo.findById(id);

        if (opMuestra.isPresent())
            return opMuestra.get();
        else
            return null;

    }

    public List<Muestra> buscarTodas() {
        return muestraRepo.findAll();
    }

    public void deleteMuestra(Muestra muestra) {
        muestraRepo.delete(muestra);
    }

    public void updateMuestra(Muestra muestra) {
        muestraRepo.save(muestra);
    }
}