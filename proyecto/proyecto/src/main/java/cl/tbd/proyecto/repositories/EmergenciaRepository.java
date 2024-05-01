package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.entities.EmergenciaEntity;

import java.util.List;
public interface EmergenciaRepository {

    List<EmergenciaEntity> findAll();
    List<EmergenciaEntity> findAllPagination(int size, int page);

    List<EmergenciaEntity> findAllUncompleted(int size, int page);

    public List<EmergenciaEntity> findHabilidadesByEmergencia(Long id_emergencia);

        EmergenciaEntity findById(Long id);
    EmergenciaEntity create(EmergenciaEntity emergencia, String actualUser);
    EmergenciaEntity update(EmergenciaEntity emergencia, String actualUser);


    Boolean delete(Long id, String actualUser); // en caso de q de error da false
}
