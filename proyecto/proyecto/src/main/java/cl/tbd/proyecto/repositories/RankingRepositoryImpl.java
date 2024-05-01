package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.controllers.DTO.VoluntarioDTO;
import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.RankingEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;

@Repository
public class RankingRepositoryImpl implements RankingRepository {

    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public List<RankingEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM ranking";
            return connection.createQuery(query).executeAndFetch(RankingEntity.class);
        }
    }

    @Override
    public List<RankingEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM ranking LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(RankingEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    @Override
    public RankingEntity findById(Long id_ranking) {
        String sqlQuery = "SELECT * FROM ranking WHERE id_ranking = :id_ranking";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_ranking", id_ranking)
                    .executeAndFetchFirst(RankingEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public int countVolInTarea(long tarea_id){
        String sqlQuery = "SELECT count(*) from ranking where id_tarea = :tarea_id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("tarea_id", tarea_id)
                    .executeScalar(Integer.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    @Override
    public List<VoluntarioDTO> getAllVoluntariosByTarea(long tarea_id){
        String sqlQuery = "SELECT nombre, rut, participa FROM public.voluntario v INNER JOIN public.ranking r on v.id_voluntario = r.id_voluntario WHERE r.id_tarea = :id_tarea ORDER BY participa DESC, nombre";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_tarea", tarea_id)
                    .executeAndFetch(VoluntarioDTO.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public RankingEntity create(RankingEntity ranking, String actualUser) {
        String sqlInsertQuery = "INSERT INTO ranking(id_voluntario, id_tarea, puntuacion, participa) VALUES(:id_voluntario, :id_tarea, :puntuacion, :participa)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long id = con.createQuery(sqlInsertQuery).bind(ranking).executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }


    @Override
    public RankingEntity update(RankingEntity ranking, String actualUser) {
        String sqlUpdateQuery = "UPDATE ranking SET id_voluntario = :id_voluntario, id_tarea = :id_tarea, puntuacion = :puntuacion WHERE id_ranking = :id_ranking";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sqlUpdateQuery)
                    .addParameter("id_voluntario", ranking.getId_voluntario())
                    .addParameter("id_tarea", ranking.getId_tarea())
                    .addParameter("puntuacion", ranking.getPuntuacion())
                    .addParameter("id_ranking", ranking.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el ranking: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM ranking WHERE id_ranking = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }
}
