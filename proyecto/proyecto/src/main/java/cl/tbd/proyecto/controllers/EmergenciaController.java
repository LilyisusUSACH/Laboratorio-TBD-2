package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.EmergenciaEntity;
import cl.tbd.proyecto.service.EmergenciaService;
import cl.tbd.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/emergencia")
public class EmergenciaController {
    @Autowired
    EmergenciaService emergenciaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/id")
    public ResponseEntity<?> getEmergenciaByid(
             @RequestParam(value = "id") Long id,
             @RequestHeader(value = "Authorization", required = false) String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(emergenciaService.getEmergenciaById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEmergencias(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization",required = false) String token
    ){
        String actualUser= usuarioService.getUser(token);

        if(size!=null){
            return ResponseEntity.ok(emergenciaService.getPageEmergencias(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(emergenciaService.getAllEmergencias());
    }
    @PostMapping("")
    public ResponseEntity<?> postEmergencia(
            @RequestBody EmergenciaEntity emergencia,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        EmergenciaEntity emergenciaEntity = emergenciaService.createEmergencia(emergencia,actualUser);
        if (emergencia!=null)
            return ResponseEntity.ok(emergenciaEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<EmergenciaEntity> updateEmergencia(
            @RequestBody EmergenciaEntity emergencia,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);

        EmergenciaEntity updatedEmergencia = emergenciaService.updateEmergencia(emergencia,actualUser);
        if (updatedEmergencia != null) {
            return ResponseEntity.ok(updatedEmergencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteEmergencia(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        if(emergenciaService.deleteEmergencia(id, actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }


}
