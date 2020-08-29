package ar.com.noaa.api.boyas.controllers;

import ar.com.noaa.api.boyas.services.BoyaService;
import ar.com.noaa.api.boyas.services.MuestraService;
import ar.com.noaa.api.boyas.entities.*;
import ar.com.noaa.api.boyas.models.request.MuestraReq;
import ar.com.noaa.api.boyas.models.response.GenericResponse;
import ar.com.noaa.api.boyas.models.response.MuestraResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MuestraController {
    @Autowired
    MuestraService muestraService;
    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<?> registrarMuestra(@RequestBody MuestraReq muestraReq) {

        GenericResponse gR = new GenericResponse();
        MuestraResponse muestraCreada = new MuestraResponse();
        Muestra muestraRegistrada = muestraService.crearMuestra(muestraReq.boyaId, muestraReq.horario,
                muestraReq.latitud, muestraReq.longitud, muestraReq.matriculaEmbarcacion, muestraReq.alturaNivelDelMar);
        if (muestraRegistrada == null) {
            gR.isOk = false;
            gR.mensaje = "No pudiste crear la muestra";
            return ResponseEntity.badRequest().body(gR);
        }

        muestraCreada.id = muestraRegistrada.getId();
        muestraCreada.color = muestraRegistrada.getBoya().getColor();

        return ResponseEntity.ok(muestraCreada);
    }

    @GetMapping("/muestras/boyas/{id}")
    public ResponseEntity<?> listarMuestrasPorBoya(@PathVariable Integer id) {
        List<Muestra> listaMuestras = boyaService.buscarBoyaPorId(id).getMuestras();
        return ResponseEntity.ok(listaMuestras);
    }

    @GetMapping("/muestras")
    public ResponseEntity<?> listarMuestras() {
        return ResponseEntity.ok(muestraService.buscarTodas());
    }

}