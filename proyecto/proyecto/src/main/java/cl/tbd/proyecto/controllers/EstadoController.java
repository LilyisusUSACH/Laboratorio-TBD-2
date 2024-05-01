package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.EstadoEntity;
import cl.tbd.proyecto.service.EstadoService;
import cl.tbd.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    EstadoService estadoService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<?> getAllEstados(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization", required = false) String token
    ){

        String actualUser= usuarioService.getUser(token);

        if(size!=null){
            return ResponseEntity.ok(estadoService.getPageEstados(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(estadoService.getAllEstados());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getEstadoById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(estadoService.getEstadoById(id));
    }


    @PostMapping("")
    public ResponseEntity<?> postEstadoEmergencia(
            @RequestBody EstadoEntity estado,
            @RequestHeader(value = "Authorization", required = false) String token){

        String actualUser= usuarioService.getUser(token);
        EstadoEntity estadoEntity = estadoService.createEstado(estado,actualUser);
        if(estadoEntity != null)
            return  ResponseEntity.ok(estadoEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<EstadoEntity> updateEstado(
            @RequestBody EstadoEntity estado,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        EstadoEntity estadoEntity = estadoService.updateEstado(estado,actualUser);
        if (estadoEntity != null) {
            return ResponseEntity.ok(estadoEntity);
        } else {
            // Este código puede variar dependiendo de cómo desees manejar los casos donde el estado de la tarea no existe.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteEstado(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token ) {

        String actualUser= usuarioService.getUser(token);


        if(estadoService.deleteEstado(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }
}
