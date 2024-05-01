package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.service.UsuarioService;
import cl.tbd.proyecto.service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/voluntario")
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<?> getAllVoluntarios(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization", required = false) String token)
            {
                String actualUser = usuarioService.getUser(token);

          if(size!=null){
            return ResponseEntity.ok(voluntarioService.getPageVoluntarios(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(voluntarioService.getAllVoluntarios());
    }
    @GetMapping("/Tarea")
    public ResponseEntity<?> getAllVoluntariosByTarea(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam("id_tarea") Long id_tarea)
    {
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(voluntarioService.getAllVoluntariosByTarea(id_tarea));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getVoluntarioById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(voluntarioService.getvoluntarioByID(id));
    }

    @PostMapping("")
    public ResponseEntity<?> postVoluntario(
            @RequestBody VoluntarioEntity voluntario,
            @RequestHeader(value ="Authorization", required = false) String token ) {
        String actualUser = usuarioService.getUser(token);

        VoluntarioEntity voluntarioEntity = voluntarioService.createVoluntario(voluntario,actualUser);
        if (voluntarioEntity!=null)
            return ResponseEntity.ok(voluntarioEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<VoluntarioEntity> updateVoluntario(
            @RequestBody VoluntarioEntity voluntario,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser = usuarioService.getUser(token);

        VoluntarioEntity updatedVoluntario = voluntarioService.updateVoluntario(voluntario, actualUser);
        if (updatedVoluntario != null) {
            return ResponseEntity.ok(updatedVoluntario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteVoluntario(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String actualUser = usuarioService.getUser(token);

        if(voluntarioService.deleteVoluntario(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }
}
