package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.TareaEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;

@Repository
public class Tarea_HabilidadRepositoryImpl implements Tarea_HabilidadRepository{

    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Tarea_HabilidadEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM tarea_habilidad";
            return connection.createQuery(query).executeAndFetch(Tarea_HabilidadEntity.class);
        }
    }
    @Override
    public List<Tarea_HabilidadEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM tarea_habilidad LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(Tarea_HabilidadEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    @Override
    public Tarea_HabilidadEntity findById(Long id_tarea_habilidad) {
        String sqlQuery = "SELECT * FROM tarea_habilidad WHERE id_tarea_habilidad = :id_tarea_habilidad";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_tarea_habilidad", id_tarea_habilidad)
                    .executeAndFetchFirst(Tarea_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public Tarea_HabilidadEntity create(Tarea_HabilidadEntity tarea_habilidad, String actualUser) {
        String sqlInsertQuery = "INSERT INTO tarea_habilidad(id_tarea, id_habilidad) VALUES(:id_tarea, :id_habilidad)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long id = con.createQuery(sqlInsertQuery).bind(tarea_habilidad).executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public Tarea_HabilidadEntity createByValues(Long id_tarea, Long id_habilidad, String actualUser) {
        String sqlInsertQuery = "INSERT INTO tarea_habilidad(id_tarea, id_habilidad) VALUES(:id_tarea, :id_habilidad)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long id = con.createQuery(sqlInsertQuery).addParameter("id_tarea",id_tarea)
                    .addParameter("id_habilidad",id_habilidad)
                    .executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public Tarea_HabilidadEntity update(Tarea_HabilidadEntity tareaHabilidad, String actualUser) {
        String sqlUpdateQuery = "UPDATE tarea_habilidad SET id_tarea = :id_tarea, id_habilidad = :id_habilidad WHERE id_tarea_habilidad = :id_tarea_habilidad";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("id_tarea", tareaHabilidad.getId_tarea())
                    .addParameter("id_habilidad", tareaHabilidad.getId_habilidad())
                    .addParameter("id_tarea_habilidad", tareaHabilidad.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar tarea_habilidad: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM tarea_habilidad WHERE id_tarea_habilidad = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }

    @Override
    public Boolean deleteAllByTarea(Long id_tarea, String actualUser){
        String sqlDeleteQuery = "DELETE FROM tarea_habilidad WHERE id_tarea = :id";
        return deleteSql(id_tarea, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }

}
