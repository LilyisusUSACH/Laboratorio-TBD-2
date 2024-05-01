package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.controllers.DTO.VoluntarioDTO;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.RankingEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;

import java.util.List;
public interface RankingRepository {

    List<RankingEntity> findAll();
    List<RankingEntity> findAllPagination(int size, int page);
    RankingEntity findById(Long id);

    int countVolInTarea(long tarea_id);

    List<VoluntarioDTO> getAllVoluntariosByTarea(long tarea_id);

    RankingEntity create(RankingEntity ranking, String actualUser);
    RankingEntity update(RankingEntity ranking, String actualUser);
    Boolean delete(Long id, String actualUser);
}
