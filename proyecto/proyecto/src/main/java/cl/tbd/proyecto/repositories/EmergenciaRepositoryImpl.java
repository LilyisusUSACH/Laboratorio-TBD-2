package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.EmergenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;

@Repository
public class EmergenciaRepositoryImpl implements EmergenciaRepository{

    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<EmergenciaEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM emergencia ORDER BY id_estado DESC, nombre";
            return connection.createQuery(query).executeAndFetch(EmergenciaEntity.class);
        }
    }

    @Override
    public List<EmergenciaEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM emergencia LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(EmergenciaEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    /*
    @Override
    public List<EmergenciaEntity> findAllUncompleted(int size, int page){
        String sqlQuery = "Select * FROM emergencia WHERE completada = 0 LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(EmergenciaEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }*/

    @Override
    public List<EmergenciaEntity> findAllUncompleted(int size, int page) {
        String sqlQuery = "SELECT e.* FROM emergencia e " +
                "INNER JOIN estado ee ON e.id_estado = ee.id_estado " +
                "WHERE ee.descripcion = 'uncompleted' " +
                "LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("size", size)
                    .addParameter("offset", offset)
                    .executeAndFetch(EmergenciaEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public List<EmergenciaEntity> findHabilidadesByEmergencia(Long id_emergencia) {
        return null;
    }

    @Override
    public EmergenciaEntity findById(Long id_emergencia) {
        String sqlQuery = "SELECT * FROM emergencia WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_emergencia", id_emergencia)
                    .executeAndFetchFirst(EmergenciaEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public EmergenciaEntity create(EmergenciaEntity emergencia, String actualUser) {
        String sqlInsertQuery =  "INSERT INTO emergencia(nombre, descripcion, fecha_inicio, fecha_fin, id_institucion, id_estado) VALUES( :nombre, :descripcion, :fecha_inicio, :fecha_fin, :id_institucion, :id_estado)";
        try(Connection connection = sql2o.open()){
            usuarioRepository.setUsername(actualUser, connection);
            Long id = connection.createQuery(sqlInsertQuery).bind(emergencia).executeUpdate().getKey(Long.class);
            return findById(id); // TODO: revisar si devolver entidad o key, o nada
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public EmergenciaEntity update(EmergenciaEntity emergencia, String actualUser) {
        String sqlUpdateQuery = "UPDATE emergencia SET nombre = :nombre, descripcion = :descripcion, fecha_inicio = :fechaInicio, fecha_fin = :fechaFin WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("fechaInicio", emergencia.getFecha_inicio())  // Asegúrate que los getters están correctamente definidos
                    .addParameter("fechaFin", emergencia.getFecha_fin())        // y se llaman igual que en la clase `EmergenciaEntity`.
                    .addParameter("id_emergencia", emergencia.getId())  // Cambia 'id' por 'id_emergencia' para que coincida con el SQL.
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar la emergencia: " + e.getMessage());
        }
        return null;
    }


    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM emergencia WHERE id_emergencia = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }}
