package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.Vol_HabilidadEntity;
import cl.tbd.proyecto.service.UsuarioService;
import cl.tbd.proyecto.service.Vol_HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/vol_habilidad")
public class Vol_HabilidadController {
    @Autowired
    Vol_HabilidadService volHabilidadService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<?> getAllVolHabilidades(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization",required = false) String token
    ){

        String actualUser= usuarioService.getUser(token);


        if(size!=null){
            return ResponseEntity.ok(volHabilidadService.getPageVolHabilidades(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(volHabilidadService.getAllVolHabilidades());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getVolHabilidadById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(volHabilidadService.getVolHabilidadById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> postVolHabilidades(
            @RequestBody Vol_HabilidadEntity volHabilidad,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);

        Vol_HabilidadEntity volHabilidadEntity = volHabilidadService.createVolHabilidades(volHabilidad, actualUser);
        if (volHabilidadEntity!=null)
            return ResponseEntity.ok(volHabilidadEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<Vol_HabilidadEntity> updateVol_Habilidad(
            @RequestBody Vol_HabilidadEntity volHabilidad,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        Vol_HabilidadEntity updatedVolHabilidad = volHabilidadService.updateVolHabilidades(volHabilidad,actualUser);
        if (updatedVolHabilidad != null) {
            return ResponseEntity.ok(updatedVolHabilidad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteVolHabilidad(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        if(volHabilidadService.deleteVolHabilidad(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }
}
