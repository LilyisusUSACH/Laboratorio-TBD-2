package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.Tarea_HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class Tarea_HabilidadService {

    @Autowired
    Tarea_HabilidadRepository tareaHabilidadRepository;

    public List<Tarea_HabilidadEntity> getAllTareaHabilidades(){
        return tareaHabilidadRepository.findAll();
    }

    public List<Tarea_HabilidadEntity> getPageTareaHabilidades(int size, int page){
        return tareaHabilidadRepository.findAllPagination(size, page);
    }

    public Tarea_HabilidadEntity getTareaHabilidadById(Long id){
        return tareaHabilidadRepository.findById(id);
    }

    public Tarea_HabilidadEntity createTareaHabilidades(Tarea_HabilidadEntity tareaHabilidad, String actualUser) {
        return tareaHabilidadRepository.create(tareaHabilidad, actualUser);
    }

    public Tarea_HabilidadEntity updateTareaHabilidades(Tarea_HabilidadEntity tareaHabilidad, String actualUser) {
        return tareaHabilidadRepository.update(tareaHabilidad, actualUser);
    }
    public Tarea_HabilidadEntity createTareaHabilidadesByValues(Long id_tarea, Long id_Habilidad, String actualUser) {
        return tareaHabilidadRepository.createByValues(id_tarea,id_Habilidad, actualUser);
    }

    public Boolean deleteTareaHabilidad(Long id, String actualUser){
        return tareaHabilidadRepository.delete(id, actualUser);
    }

    public Boolean deleteAllTareaHabilidadByTarea(Long id_tarea, String actualUser){
        return tareaHabilidadRepository.deleteAllByTarea(id_tarea,actualUser);
    }


}
