package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.models.geo.GeoVolunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class GeoRepositoryImpl implements GeoRepository{

    @Autowired
    private Sql2o sql2o;

    public List<GeoVolunteer> findVoluntariosByEmergencia(Long id_emergencia) {
        String sqlQuery = "SELECT DISTINCT v.id_voluntario, v.rut, v.nombre, v.direccion, v.email, v.telefono, ST_AsText(v.ubicacion) as geoText FROM voluntario v " +
                "INNER JOIN ranking r ON v.id_voluntario = r.id_voluntario " +
                "INNER JOIN tarea t ON r.id_tarea = t.id_tarea " +
                "WHERE t.id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", id_emergencia)
                    .executeAndFetch(GeoVolunteer.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
