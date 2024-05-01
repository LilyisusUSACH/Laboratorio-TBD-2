package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.TareaEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    HabilidadService habilidadService;

    @Autowired
    Tarea_HabilidadService tareaHabilidadService;

    public List<TareaEntity> getAllTareas(){
        return tareaRepository.findAll();
    }

    public List<TareaEntity> getTareasByEmergencia(Long id_emergencia){ return tareaRepository.findByEmergencia(id_emergencia); }


    public List<TareaEntity> getPageTareas(int size, int page){
        return tareaRepository.findAllPagination(size, page);
    }

    public TareaEntity getTareaByID(Long id){
        return tareaRepository.findById(id);
    }

    public TareaEntity createTarea(TareaEntity tarea, String actualUser) {
        return tareaRepository.create(tarea,actualUser);
    }

    public TareaEntity updateTarea(TareaEntity tarea, String actualUser) { return tareaRepository.update(tarea,actualUser);}
    public Tarea_HabilidadEntity addHabilidadToTarea(TareaEntity tarea, Long id_habilidad, String actualUser){
        return tareaHabilidadService.createTareaHabilidadesByValues(tarea.getId(), id_habilidad, actualUser);
    }

    public List<Tarea_HabilidadEntity> addHabilidadesToTarea(TareaEntity tarea, List<Long> id_habilidades, String actualUser){
        List<Tarea_HabilidadEntity> tareaHabilidadEntities = new ArrayList<>();
        id_habilidades.forEach(habilidad -> tareaHabilidadEntities.add(addHabilidadToTarea(tarea,habilidad,actualUser)));
        return tareaHabilidadEntities;
    }

    public Boolean deleteTarea(Long id,String actualUser){
        return tareaRepository.delete(id,actualUser);
    }

}
