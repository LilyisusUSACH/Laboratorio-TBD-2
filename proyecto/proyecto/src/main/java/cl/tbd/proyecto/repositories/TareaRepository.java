package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.TareaEntity;
import cl.tbd.proyecto.entities.Vol_HabilidadEntity;

import java.util.List;
public interface TareaRepository {

    List<TareaEntity> findAll();
    List<TareaEntity> findAllPagination(int size, int page);
    TareaEntity create(TareaEntity tarea, String actualUser);
    TareaEntity findById(Long id);
    TareaEntity update(TareaEntity tarea, String actualUser);
    Boolean delete(Long id, String actualUser);

    List<TareaEntity> findByEmergencia(Long id_emergencia);

}
