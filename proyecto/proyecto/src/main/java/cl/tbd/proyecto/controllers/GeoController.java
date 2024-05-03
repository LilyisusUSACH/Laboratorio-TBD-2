package cl.tbd.proyecto.controllers;


import cl.tbd.proyecto.models.geo.GeoFeature;
import cl.tbd.proyecto.models.geo.GeoMulti;
import cl.tbd.proyecto.models.geo.GeoVolunteer;
import cl.tbd.proyecto.models.geo.Geometry;
import cl.tbd.proyecto.service.GeoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/geo")
public class GeoController {

    @Autowired
    GeoServices geoServices;

    @GetMapping("/volByEme")
    public ResponseEntity<?> getAllEstados(
            @RequestParam(value = "id_emergencia", required = false) Long id_emergencia,
            @RequestHeader(value = "Authorization", required = false) String token
    ){
        List<GeoVolunteer> geos = geoServices.findAllVolunteersByEmergency(id_emergencia);
        GeoMulti geoMulti = new GeoMulti("FEATURE COLLECTION", new ArrayList<>());
        geos.forEach( geoVolunteer -> {
            Geometry geometry = new Geometry("POINT", geoVolunteer.getCoordenadas());
            GeoFeature geoFeature = new GeoFeature("FEATURE",geometry, Map.of(
                    "id",geoVolunteer.getId(),
                    "rut",geoVolunteer.getRut(),
                    "nombre",geoVolunteer.getNombre(),
                    "direccion",geoVolunteer.getDireccion(),
                    "email",geoVolunteer.getEmail(),
                    "telefono",geoVolunteer.getTelefono()
            ));
            geoMulti.getFeatures().add(geoFeature);
        });
        return new ResponseEntity<>(geoMulti, HttpStatus.OK);
    }
}
