package cl.tbd.proyecto.service;


import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.InstitucionEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.HabilidadRepository;
import cl.tbd.proyecto.repositories.InstitucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class HabilidadService{

    @Autowired
    HabilidadRepository habilidadRepository;
    public List<HabilidadEntity> getAllHabilidades(){
        return habilidadRepository.findAll();
    }

    public List<HabilidadEntity> getPageHabilidades(int size, int page){
        return habilidadRepository.findAllPagination(size, page);
    }

    public HabilidadEntity getHabilidadById(Long id){
        return habilidadRepository.findById(id);
    }

    public List<HabilidadEntity> findHabilidadesByEmergencia(Long id_emergencia){
        return habilidadRepository.findHabilidadesByEmergencia(id_emergencia);
    }

    public List<HabilidadEntity> findHabilidadesByTarea(Long id_tarea){
        return habilidadRepository.findHabilidadesByTarea(id_tarea);
    }

    public HabilidadEntity createHabilidad(HabilidadEntity habilidad, String actualUser) {
        return habilidadRepository.create(habilidad, actualUser);
    }

    public Boolean deleteHabilidad(Long id, String actualUser){
        return habilidadRepository.delete(id, actualUser);
    }

    public HabilidadEntity updateHabilidad(HabilidadEntity habilidad, String actualUser) {return habilidadRepository.update(habilidad, actualUser);}
}
