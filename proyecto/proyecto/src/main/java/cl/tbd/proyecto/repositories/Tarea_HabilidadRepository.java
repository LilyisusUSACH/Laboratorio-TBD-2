package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;


import java.util.List;
public interface Tarea_HabilidadRepository {

    List<Tarea_HabilidadEntity> findAll();
    List<Tarea_HabilidadEntity> findAllPagination(int size, int page);
    Tarea_HabilidadEntity findById(Long id);


    Tarea_HabilidadEntity create(Tarea_HabilidadEntity tarea_habilidad, String actualUser);
    Tarea_HabilidadEntity update(Tarea_HabilidadEntity tarea_habilidad, String actualUser);
    Tarea_HabilidadEntity createByValues(Long id_tarea, Long id_habilidad, String actualUser);
    Boolean delete(Long id, String actualUser);

    Boolean deleteAllByTarea(Long id_tarea, String actualUser);
}
