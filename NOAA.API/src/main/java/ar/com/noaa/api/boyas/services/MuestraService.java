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

    public Muestra crearMuestra(Integer boyaId, Date horarioMuestra, Double latitud, Double longitud,
            String matriculaEmbarcacion, Double altura) {
        Boya boya = boyaService.buscarBoyaPorId(boyaId);
        if (boya != null) {

            Muestra muestra = new Muestra();
            muestra.setAlturaMar(altura);
            muestra.setHorario(horarioMuestra);
            muestra.setLatitud(latitud);
            muestra.setLongitud(longitud);
            muestra.setMatricula(matriculaEmbarcacion);
            boya.agregarMuestra(muestra);

            boya.setColor(this.encontrarColor(muestra.getAlturaMar()));
            grabarMuestra(muestra);
            // Devuelve la ultima muestra agregada qeu es la mia.
            // ya que actualizo por boya
            return boya.getMuestras().get(boya.getMuestras().size() - 1);
       } else {
            return null;
        }

    }

   


    public String encontrarColor(Double altura) {
        //Este orden de if sigue estando mal pero para probar con el Unit Test
        //challenge pide: menos de -50 o mas de 50 AMARILLO
        //menos de -100 o mas de 100 que ROJO
        //SI No, VERDE
        
        if (altura < -100 || altura > 100) {
            return "ROJO";
        }
        else if (altura < -50 || altura > 50) {
            return "AMARILLO";
        } else {
            return "VERDE";
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