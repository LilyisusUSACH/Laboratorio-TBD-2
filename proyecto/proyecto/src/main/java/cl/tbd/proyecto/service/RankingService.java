package cl.tbd.proyecto.service;

import cl.tbd.proyecto.controllers.DTO.VoluntarioDTO;
import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.RankingEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    RankingRepository rankingRepository;

    public List<RankingEntity> getAllRankings(){
        return rankingRepository.findAll();
    }

    public List<RankingEntity> getPageRankings(int size, int page){
        return rankingRepository.findAllPagination(size, page);
    }

    public int countVolInTarea(long tarea_id){
        return rankingRepository.countVolInTarea(tarea_id);
    }

    public List<VoluntarioDTO> findAllVolByTarea(long id_tarea){
        return rankingRepository.getAllVoluntariosByTarea(id_tarea);
    }

    public RankingEntity getRankingById(Long id){
        return rankingRepository.findById(id);
    }
    public RankingEntity createRanking(RankingEntity ranking, String actualUser) {
        return rankingRepository.create(ranking, actualUser);
    }

    public RankingEntity updateRanking(RankingEntity ranking, String actualUser) {return rankingRepository.update(ranking, actualUser);}

    public Boolean deleteRanking(Long id, String actualUser){
        return rankingRepository.delete(id, actualUser);
    }

}
