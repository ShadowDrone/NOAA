package ar.com.noaa.api.boyas.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.noaa.api.boyas.entities.*;
import ar.com.noaa.api.boyas.models.response.GenericResponse;
import ar.com.noaa.api.boyas.services.*;

@RestController
public class BoyaController {

    @Autowired
    BoyaService boyaService;

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> obtnerBoyas() {

        List<Boya> listaADevolver = boyaService.obtenerBoyas();

        return ResponseEntity.ok(listaADevolver);

        // return ResponseEntity.ok(boyaService.obtnerBoyas());
    }

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crearBoya(@RequestBody Boya boya) {
        boyaService.crearBoya(boya);

        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.id = boya.getId();
        gR.mensaje = "Boya creada con exito";
        return ResponseEntity.ok(gR);
    }
}