package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;

@Repository
public class VoluntarioRepositoryImpl implements VoluntarioRepository {
    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<VoluntarioEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM voluntario";
            return connection.createQuery(query).executeAndFetch(VoluntarioEntity.class);
        }
    }

    @Override
    public List<VoluntarioEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM voluntario LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(VoluntarioEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public List<VoluntarioEntity> findAllVoluntariosByTarea(long tarea_id){
        String sqlQuery = "SELECT v.* FROM voluntario v INNER JOIN public.ranking on v.id_voluntario = ranking.id_voluntario " +
                "INNER JOIN public.tarea t on ranking.id_tarea = t.id_tarea WHERE t.id_tarea = :id_tarea";
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery)
                    .addParameter("id_tarea",tarea_id)
                    .executeAndFetch(VoluntarioEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public VoluntarioEntity findById(Long id_voluntario) {
        String sqlQuery = "SELECT * FROM voluntario WHERE id_voluntario = :id_voluntario";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_voluntario", id_voluntario)
                    .executeAndFetchFirst(VoluntarioEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public VoluntarioEntity findByRut(String rut) {
        String sqlQuery = "SELECT * FROM voluntario WHERE rut = :rut";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("rut", rut)
                    .executeAndFetchFirst(VoluntarioEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public VoluntarioEntity create(VoluntarioEntity voluntario, String actualUser) {
        String sqlInsertQuery = "INSERT INTO voluntario (user_id, nombre, edad, direccion, genero, email, telefono,rut) " +
                "VALUES(:user_id, :nombre, :edad, :direccion, :genero, :email, :telefono, :rut)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long insertedId = con.createQuery(sqlInsertQuery, true)
                    .addParameter("user_id", voluntario.getUserId())
                    .bind(voluntario)
                    .executeUpdate()
                    .getKey(Long.class);
            voluntario.setId(insertedId); //Actualizar el id del voluntario
            return voluntario;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public VoluntarioEntity update(VoluntarioEntity voluntario, String actualUser) {
        final String sqlUpdateQuery = "UPDATE voluntario SET user_id = :user_id, nombre = :nombre, edad = :edad, " +
                "direccion = :direccion, genero = :genero, email = :email, telefono = :telefono WHERE id_voluntario = :id_voluntario";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .bind(voluntario)
                    .addParameter("id_voluntario", voluntario.getId())
                    .executeUpdate();
            return voluntario;
        } catch (Exception e) {
            System.out.println("Error al actualizar el voluntario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM voluntario WHERE id_voluntario = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }
}
