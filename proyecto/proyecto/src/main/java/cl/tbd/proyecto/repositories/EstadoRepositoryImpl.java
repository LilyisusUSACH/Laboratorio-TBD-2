package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.EstadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {
    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public List<EstadoEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM estado";
            return connection.createQuery(query).executeAndFetch(EstadoEntity.class);
        }
    }

    @Override
    public List<EstadoEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM estado LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(EstadoEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    @Override
    public EstadoEntity findById(Long id_estado) {
        String sqlQuery = "SELECT * FROM estado WHERE id_estado = :id_estado";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_estado", id_estado)
                    .executeAndFetchFirst(EstadoEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public EstadoEntity create(EstadoEntity estado, String actualUser) {
        String sqlInsertQuery = "INSERT INTO estado(descripcion) VALUES(:descripcion)";
        try (Connection connection = sql2o.open()){
            Long id = connection.createQuery(sqlInsertQuery).bind(estado).executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e){
            System.out.println("Error: " + e );
            return null;
        }
    }

    @Override
    public EstadoEntity update(EstadoEntity estado, String actualUser) {
        String sqlUpdateQuery = "UPDATE estado SET descripcion = :descripcion WHERE id_estado = :id_estado";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("descripcion", estado.getDescripcion())
                    .addParameter("id_estado", estado.getId_estado())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el estado de la emergencia: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM estado WHERE id_estado = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }

    static Boolean deleteSql(Long id, String actualUser, String sqlDeleteQuery, Sql2o sql2o, UsuarioRepository usuarioRepository) {
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlDeleteQuery)
                    .addParameter("id", id)
                    .executeUpdate();
            return true;
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

}
