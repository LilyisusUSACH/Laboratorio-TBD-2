package cl.tbd.proyecto.repositories;
import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.HabilidadEntity;
import org.sql2o.Query;

import java.util.List;

public interface Eme_HabilidadRepository {

    List<Eme_HabilidadEntity> findAll();

    List<Eme_HabilidadEntity> findAllPagination(int size, int page);

    Eme_HabilidadEntity create(Eme_HabilidadEntity eme_habilidad, String actualUser);

    List<HabilidadEntity> findHabilidadesByEmergencia(Long id_emergencia);

    Eme_HabilidadEntity findById(Long id);

    Eme_HabilidadEntity update(Eme_HabilidadEntity eme_habilidad, String actualUser);

    Boolean delete(Long id, String actualUser);

}
