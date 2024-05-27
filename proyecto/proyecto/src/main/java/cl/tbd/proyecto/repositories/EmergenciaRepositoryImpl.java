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
            String query = "SELECT id_emergencia, nombre, descripcion, fecha_inicio, fecha_fin, id_institucion, id_estado, ST_X(ubicacion::geometry) AS longitud, ST_Y(ubicacion::geometry) AS latitud FROM emergencia ORDER BY id_estado DESC, nombre";
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
        String sqlQuery = "SELECT e.id_emergencia, e.nombre, e.descripcion, e.fecha_inicio, e.fecha_fin, e.id_institucion, e.id_estado, ST_X(ubicacion::geometry) AS longitud, ST_Y(ubicacion::geometry) AS latitud FROM emergencia e " +
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
        String sqlQuery = "SELECT id_emergencia, nombre, descripcion, fecha_inicio, fecha_fin, id_institucion, id_estado, ST_X(ubicacion::geometry) AS longitud, ST_Y(ubicacion::geometry) AS latitud FROM emergencia WHERE id_emergencia = :id_emergencia";
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
        String sqlInsertQuery = "INSERT INTO emergencia(nombre, descripcion, fecha_inicio, fecha_fin, id_institucion, id_estado, ubicacion) VALUES(:nombre, :descripcion, :fecha_inicio, :fecha_fin, :id_institucion, :id_estado, ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326))";
        try (Connection connection = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, connection);
            Long id = connection.createQuery(sqlInsertQuery)
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("fecha_inicio", emergencia.getFecha_inicio())
                    .addParameter("fecha_fin", emergencia.getFecha_fin())
                    .addParameter("id_institucion", emergencia.getId_institucion())
                    .addParameter("id_estado", emergencia.getId_estado())
                    .addParameter("latitud", emergencia.getLatitud())
                    .addParameter("longitud", emergencia.getLongitud())
                    .executeUpdate()
                    .getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }



    @Override
    public EmergenciaEntity update(EmergenciaEntity emergencia, String actualUser) {
        String sqlUpdateQuery = "UPDATE emergencia SET nombre = :nombre, descripcion = :descripcion, fecha_inicio = :fechaInicio, fecha_fin = :fechaFin, ubicacion = ST_SetSRID(ST_MakePoint(:longitud, :latitud), 4326) WHERE id_emergencia = :id_emergencia";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("fechaInicio", emergencia.getFecha_inicio())
                    .addParameter("fechaFin", emergencia.getFecha_fin())
                    .addParameter("latitud", emergencia.getLatitud())
                    .addParameter("longitud", emergencia.getLongitud())
                    .addParameter("id_emergencia", emergencia.getId())
                    .executeUpdate();
            return findById(emergencia.getId()); // Retorna la emergencia actualizada para confirmación o uso posterior
        } catch (Exception e) {
            System.out.println("Error al actualizar la emergencia: " + e.getMessage());
            return null;
        }
    }



    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM emergencia WHERE id_emergencia = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }}
