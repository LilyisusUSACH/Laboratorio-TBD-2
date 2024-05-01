package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import java.util.List;
public interface VoluntarioRepository {

    List<VoluntarioEntity> findAll();

    List<VoluntarioEntity> findAllPagination(int size, int page);

    List<VoluntarioEntity> findAllVoluntariosByTarea(long tarea_id);

    VoluntarioEntity findById(Long id);

    VoluntarioEntity findByRut(String rut);


    VoluntarioEntity create(VoluntarioEntity voluntario, String actualUser);


    VoluntarioEntity update(VoluntarioEntity voluntario, String actualUser);

    Boolean delete(Long id, String actualUser);

}
