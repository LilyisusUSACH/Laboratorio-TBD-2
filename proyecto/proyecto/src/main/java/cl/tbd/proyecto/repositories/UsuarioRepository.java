package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.UsuarioEntity;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import java.util.List;

public interface UsuarioRepository {

    List<UsuarioEntity> findAll();

    UsuarioEntity findById(Long id);

    UsuarioEntity findByUsername(String username);

    Boolean Login(String username, String Password);

    UsuarioEntity create(UsuarioEntity usuario, String actualUser);

    UsuarioEntity update(UsuarioEntity usuario, String actualUser);

    void setUsername(String username, Connection connection);

    Boolean delete(Long id, String actualUser);

}
