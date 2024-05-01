package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.HabilidadEntity;
import cl.tbd.proyecto.service.HabilidadService;
import cl.tbd.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/habilidad")
public class HabilidadController {
    @Autowired
    HabilidadService habilidadService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<?> getAllHabilidades(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization",required = false) String token
    ){
        String actualUser= usuarioService.getUser(token);


        if(size!=null){
            return ResponseEntity.ok(habilidadService.getPageHabilidades(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(habilidadService.getAllHabilidades());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getHabilidadById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(habilidadService.getHabilidadById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> postHabilidad(
            @RequestBody HabilidadEntity habilidad,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        HabilidadEntity habilidadEntity = habilidadService.createHabilidad(habilidad,actualUser);
        if (habilidadEntity!=null)
            return ResponseEntity.ok(habilidadEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<HabilidadEntity> updateHabilidad(
            @RequestBody HabilidadEntity habilidadActualizada,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        HabilidadEntity updatedHabilidad = habilidadService.updateHabilidad(habilidadActualizada,actualUser);
        if (updatedHabilidad != null) {
            return ResponseEntity.ok(updatedHabilidad);
        } else {
            // Este código puede variar dependiendo de cómo desees manejar los casos donde la habilidad no existe.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteHabilidad(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);
        if(habilidadService.deleteHabilidad(id,actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/byEmergency")
    public ResponseEntity<?> getHabilidadesByEmergencia(
            @RequestParam("id") Long id_emergencia,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(habilidadService.findHabilidadesByEmergencia(id_emergencia));
    }

    @GetMapping("/byTarea")
    public ResponseEntity<?> getHabilidadesByTarea(
            @RequestParam("id") Long id_tarea,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(habilidadService.findHabilidadesByTarea(id_tarea));
    }

}
