package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.RolEntity;
import java.util.List;

public interface RolRepository {

    List<RolEntity> findAll();

    RolEntity findById(Long id);

    RolEntity create(RolEntity rol, String actualUser);

    RolEntity update(RolEntity rol, String actualUser);

    Boolean delete(Long id, String actualUser);

}
