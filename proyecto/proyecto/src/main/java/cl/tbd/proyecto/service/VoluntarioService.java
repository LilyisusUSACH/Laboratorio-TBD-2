package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.Eme_HabilidadRepository;
import cl.tbd.proyecto.repositories.VoluntarioRepository;
import cl.tbd.proyecto.repositories.VoluntarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class VoluntarioService {
    @Autowired
    VoluntarioRepository voluntarioRepository;

    public List<VoluntarioEntity> getAllVoluntarios(){
        return voluntarioRepository.findAll();
    }

    public List<VoluntarioEntity> getPageVoluntarios(int size, int page){
        return voluntarioRepository.findAllPagination(size, page);
    }

    public VoluntarioEntity getvoluntarioByID(Long id){
        return voluntarioRepository.findById(id);
    }

    public List<VoluntarioEntity> getAllVoluntariosByTarea(Long id_tarea){
        return voluntarioRepository.findAllVoluntariosByTarea(id_tarea);
    }

    public VoluntarioEntity createVoluntario(VoluntarioEntity voluntario, String actualUser) {
        return voluntarioRepository.create(voluntario,actualUser);
    }

    public VoluntarioEntity updateVoluntario(VoluntarioEntity voluntario, String actualUser) {
        return voluntarioRepository.update(voluntario, actualUser);
    }

    public Boolean deleteVoluntario(Long id, String actualUser){
        return voluntarioRepository.delete(id, actualUser);
    }
}

