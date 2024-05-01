package cl.tbd.proyecto.service;

import cl.tbd.proyecto.entities.EstadoEntity;
import cl.tbd.proyecto.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public List<EstadoEntity> getAllEstados(){
        return estadoRepository.findAll();
    }

    public EstadoEntity getEstadoById(Long id){
        return estadoRepository.findById(id);
    }

    public List<EstadoEntity> getPageEstados(int size, int page){
        return estadoRepository.findAllPagination(size, page);
    }

    public EstadoEntity createEstado(EstadoEntity estado, String actualUser) {
        return estadoRepository.create(estado,actualUser);
    }

    public Boolean deleteEstado(Long id, String actualUser){
        return estadoRepository.delete(id, actualUser);
    }

    public EstadoEntity updateEstado(EstadoEntity estado, String actualUser) {
        return estadoRepository.update(estado, actualUser);
    }


}
