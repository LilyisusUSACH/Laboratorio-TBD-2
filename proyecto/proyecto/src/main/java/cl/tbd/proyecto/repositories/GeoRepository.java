package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.models.geo.GeoVolunteer;

import java.util.List;

public interface GeoRepository {
    List<GeoVolunteer> findVoluntariosByEmergencia(Long id_emergencia);

}
