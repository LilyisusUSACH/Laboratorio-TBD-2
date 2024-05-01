package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.EstadoEntity;
import java.util.List;
public interface EstadoRepository {

    List<EstadoEntity> findAll();
    List<EstadoEntity> findAllPagination(int size, int page);
    EstadoEntity findById(Long id);

    EstadoEntity create(EstadoEntity estado, String actualUser);

    EstadoEntity update(EstadoEntity estado, String actualUser);

    Boolean delete(Long id, String actualUser);
}
