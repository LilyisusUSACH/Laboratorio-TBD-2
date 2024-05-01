package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.Tarea_HabilidadEntity;
import cl.tbd.proyecto.service.Tarea_HabilidadService;
import cl.tbd.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tarea_habilidad")
public class Tarea_HabilidadController {
    @Autowired
    Tarea_HabilidadService tareaHabilidadService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<?> getAllTareaHabilidades(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization", required = false) String token
    ){

        String actualUser= usuarioService.getUser(token);

        if(size!=null){
            return ResponseEntity.ok(tareaHabilidadService.getPageTareaHabilidades(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(tareaHabilidadService.getAllTareaHabilidades());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getTarea_habilidadById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(tareaHabilidadService.getTareaHabilidadById(id));
    }


    @PostMapping("")
    public ResponseEntity<?> postTareaHabilidad(
            @RequestBody Tarea_HabilidadEntity tareaHabilidad,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        Tarea_HabilidadEntity tareaHabilidadEntity = tareaHabilidadService.createTareaHabilidades(tareaHabilidad,actualUser);
        if (tareaHabilidadEntity!=null)
            return ResponseEntity.ok(tareaHabilidadEntity);
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("")
    public ResponseEntity<Tarea_HabilidadEntity> updateTareaHabilidad(
            @RequestBody Tarea_HabilidadEntity tareaHabilidad,
            @RequestHeader(value = "Authorization",required = false) String token) {


        String actualUser= usuarioService.getUser(token);

        Tarea_HabilidadEntity updatedTareaHabilidad = tareaHabilidadService.updateTareaHabilidades(tareaHabilidad, actualUser);
        if (updatedTareaHabilidad != null) {
            return ResponseEntity.ok(updatedTareaHabilidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteTareaHabilidad(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        if(tareaHabilidadService.deleteTareaHabilidad(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/byTarea")
    public ResponseEntity<?> deleteTareaHabilidadByTarea(
            @RequestParam("id_tarea") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);
        if(tareaHabilidadService.deleteAllTareaHabilidadByTarea(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }
}
