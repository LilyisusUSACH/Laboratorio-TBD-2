package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class EmergenciaService {

    @Autowired
    EmergenciaRepository emergenciaRepository;

    @Autowired
    HabilidadService habilidadService;

    public List<EmergenciaEntity> getAllEmergencias(){
        return emergenciaRepository.findAll();
    }

    public List<EmergenciaEntity> getAllUncompletedEmergencias(int size, int page){
        return emergenciaRepository.findAllUncompleted(size, page);
    }


    public List<EmergenciaEntity> getPageEmergencias(int size, int page){
        return emergenciaRepository.findAllPagination(size, page);
    }

    public EmergenciaEntity createEmergencia(EmergenciaEntity emergencia, String actualUser) {
        return emergenciaRepository.create(emergencia, actualUser);
    }

    public Boolean deleteEmergencia(Long id, String actualUser){
        return emergenciaRepository.delete(id, actualUser);
    }

    public EmergenciaEntity updateEmergencia(EmergenciaEntity emergencia, String actualUser) {
        return emergenciaRepository.update(emergencia, actualUser);
    }

    public EmergenciaEntity getEmergenciaById(Long id){
        return emergenciaRepository.findById(id);
    }
}
