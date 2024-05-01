package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class RolRepositoryImpl implements RolRepository {
    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<RolEntity> findAll() {
        final String sqlQuery = "SELECT * FROM roles";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery).executeAndFetch(RolEntity.class);
        }
    }

    @Override
    public RolEntity findById(Long id) {
        final String sqlQuery = "SELECT * FROM roles WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id", id)
                    .executeAndFetchFirst(RolEntity.class);
        }
    }

    @Override
    public RolEntity create(RolEntity rol, String actualUser) {
        final String sqlInsertQuery = "INSERT INTO roles (nombre_rol) VALUES (:nombreRol)";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            Long insertedId = con.createQuery(sqlInsertQuery, true)
                    .bind(rol)
                    .executeUpdate()
                    .getKey(Long.class);
            rol.setId(insertedId);
            return rol;
        }
    }

    @Override
    public RolEntity update(RolEntity rol, String actualUser) {
        final String sqlUpdateQuery = "UPDATE roles SET nombre_rol = :nombreRol WHERE id = :id";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .bind(rol)
                    .executeUpdate();
            return rol;
        }
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        final String sqlDeleteQuery = "DELETE FROM roles WHERE id = :id";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlDeleteQuery)
                    .addParameter("id", id)
                    .executeUpdate();
            return true;
        }
    }
}
