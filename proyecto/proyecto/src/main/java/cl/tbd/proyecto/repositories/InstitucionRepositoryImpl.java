package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.InstitucionEntity;
import cl.tbd.proyecto.entities.RankingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;


import java.util.List;

@Repository
public class InstitucionRepositoryImpl implements InstitucionRepository{

    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<InstitucionEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM institucion";
            return connection.createQuery(query).executeAndFetch(InstitucionEntity.class);
        }
    }

    @Override
    public List<InstitucionEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM institucion LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(InstitucionEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public InstitucionEntity findById(Long id_institucion) {
        String sqlQuery = "SELECT * FROM institucion WHERE id_institucion = :id_institucion";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_institucion", id_institucion)
                    .executeAndFetchFirst(InstitucionEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public InstitucionEntity create(InstitucionEntity institucion, String actualUser) {
        String sqlInsertQuery = "INSERT INTO institucion(nombre, email, telefono, ubicacion) VALUES(:nombre, :email, :telefono, :ubicacion)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long id = con.createQuery(sqlInsertQuery).bind(institucion).executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public InstitucionEntity update(InstitucionEntity institucion, String actualUser) {
        String sqlUpdateQuery = "UPDATE institucion SET nombre = :nombre, email = :email, telefono = :telefono, ubicacion = :ubicacion WHERE id_institucion = :id_institucion";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("email", institucion.getEmail())
                    .addParameter("telefono", institucion.getTelefono())
                    .addParameter("ubicacion", institucion.getUbicacion())
                    .addParameter("id_institucion", institucion.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar la institución: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM institucion WHERE id_institucion = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }
}
