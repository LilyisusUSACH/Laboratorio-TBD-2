package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.InstitucionEntity;
import cl.tbd.proyecto.repositories.InstitucionRepository;
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
public class InstitucionService{
    @Autowired
    InstitucionRepository institucionRepository;

    public List<InstitucionEntity> getAllInstituciones(){
        return institucionRepository.findAll();
    }

    public List<InstitucionEntity> getPageInstituciones(int size, int page){
        return institucionRepository.findAllPagination(size, page);
    }

    public InstitucionEntity getInstitucionById(Long id){
        return institucionRepository.findById(id);
    }
    public InstitucionEntity createInstitucion(InstitucionEntity institucion, String actualUser){
        return institucionRepository.create(institucion, actualUser);
    }

    public Boolean deleteInstitucion(Long id, String actualUser){
        return institucionRepository.delete(id, actualUser);
    }

    public InstitucionEntity updateInstitucion(InstitucionEntity institucion, String actualUser)
    { return institucionRepository.update(institucion, actualUser);}
}
