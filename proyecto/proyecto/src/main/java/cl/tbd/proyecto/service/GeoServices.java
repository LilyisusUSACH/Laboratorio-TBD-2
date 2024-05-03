package cl.tbd.proyecto.service;

import ch.qos.logback.core.joran.sanity.Pair;
import cl.tbd.proyecto.models.geo.GeoVolunteer;
import cl.tbd.proyecto.repositories.GeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeoServices {

    @Autowired
    GeoRepository geoRepository;

    public List<GeoVolunteer> findAllVolunteersByEmergency(Long id_emergencia){
        List<GeoVolunteer> geos = geoRepository.findVoluntariosByEmergencia(id_emergencia);
        geos.forEach( geoVolunteer -> {
            geoVolunteer.setCoordenadas(convertGeometryTextToList(geoVolunteer.getGeoText()));
        });
        return geos;
    }

    private List<Double> convertGeometryTextToList(String geometryText) {
        // Supongamos que el texto tiene el formato "POINT (longitude latitude)"
        String[] parts = geometryText.replaceAll("[()]", "").split(" ");
        double longitude = Double.parseDouble(parts[1]);
        double latitude = Double.parseDouble(parts[2]);

        return new ArrayList<>(Arrays.asList(longitude,latitude));
    }
}
