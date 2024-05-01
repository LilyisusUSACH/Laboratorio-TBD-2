package cl.tbd.proyecto.repositories;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.Vol_HabilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

import static cl.tbd.proyecto.repositories.EstadoRepositoryImpl.deleteSql;

@Repository
public class Vol_HabilidadRepositoryImpl implements Vol_HabilidadRepository {

    @Autowired
    private Sql2o sql2o;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Vol_HabilidadEntity> findAll() {
        try (Connection connection = sql2o.open()) {
            String query = "SELECT * FROM vol_habilidad";
            return connection.createQuery(query).executeAndFetch(Vol_HabilidadEntity.class);
        }
    }
    @Override
    public List<Vol_HabilidadEntity> findAllPagination(int size, int page){
        String sqlQuery = "Select * FROM vol_habilidad LIMIT :size OFFSET :offset";
        int offset = (page - 1) * size;
        try(Connection con = sql2o.open()){
            return con.createQuery(sqlQuery).addParameter("size", size)
                    .addParameter("offset",offset).executeAndFetch(Vol_HabilidadEntity.class);
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    @Override
    public Vol_HabilidadEntity findById(Long id_vol_habilidad) {
        String sqlQuery = "SELECT * FROM vol_habilidad WHERE id_vol_habilidad = :id_vol_habilidad";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sqlQuery)
                    .addParameter("id_vol_habilidad", id_vol_habilidad)
                    .executeAndFetchFirst(Vol_HabilidadEntity.class);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public Vol_HabilidadEntity create(Vol_HabilidadEntity vol_habilidad, String actualUser) {
        String sqlInsertQuery = "INSERT INTO vol_habilidad(id_voluntario, id_habilidad, nivel_proficiencia) VALUES(:id_voluntario, :id_habilidad, :nivel_proficiencia)";
        try (Connection con = sql2o.open()){
            usuarioRepository.setUsername(actualUser, con);
            Long id = con.createQuery(sqlInsertQuery).bind(vol_habilidad).executeUpdate().getKey(Long.class);
            return findById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @Override
    public Vol_HabilidadEntity update(Vol_HabilidadEntity volHabilidad, String actualUser) {
        String sql = "UPDATE vol_habilidad SET id_voluntario = :id_voluntario, id_habilidad = :id_habilidad, nivel_proficiencia = :nivel_proficiencia WHERE id_vol_habilidad = :id_vol_habilidad";
        try (Connection con = sql2o.open()) {
            usuarioRepository.setUsername(actualUser, con);
            con.createQuery(sql)
                    .addParameter("id_voluntario", volHabilidad.getId_voluntario())
                    .addParameter("id_habilidad", volHabilidad.getId_habilidad())
                    .addParameter("nivel_proficiencia", volHabilidad.getNivel_proficiencia())
                    .addParameter("id_vol_habilidad", volHabilidad.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar la habilidad del voluntario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Long id, String actualUser) {
        String sqlDeleteQuery = "DELETE FROM vol_habilidad WHERE id_vol_habilidad = :id";
        return deleteSql(id, actualUser, sqlDeleteQuery, sql2o, usuarioRepository);
    }
}
