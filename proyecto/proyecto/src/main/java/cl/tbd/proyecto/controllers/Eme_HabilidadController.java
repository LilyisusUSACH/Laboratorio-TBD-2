package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.Eme_HabilidadEntity;
import cl.tbd.proyecto.service.Eme_HabilidadService;
import cl.tbd.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/eme_habilidad")
public class Eme_HabilidadController {
    @Autowired
    Eme_HabilidadService emeHabilidadService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<?> getAllVoluntarios(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization",required = false) String token
    ){
        String actualUser= usuarioService.getUser(token);

        if(size!=null){
            return ResponseEntity.ok(emeHabilidadService.getPageEmeHabilidades(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(emeHabilidadService.getAllEmeHabilidades());
    }

    @GetMapping("/emergencia")
    public ResponseEntity<?> getHabilidadesByEmergencia(
            @RequestParam("id_emergencia") Long id_emergencia,
            @RequestHeader(value = "Authorization",required = false) String token){

        String actualUser= usuarioService.getUser(token);


        /*Supongo que habrà alguna logica de comprobacion y autenticacion aca, como en todos los demas controllers*/

        return ResponseEntity.ok(emeHabilidadService.findHabilidadesByEmergencia(id_emergencia));
    }
    @GetMapping("/id")
    public ResponseEntity<?> getEme_HabilidadById(
            @RequestParam(value = "id_eme_hab") Long id,
            @RequestHeader(value = "Authorization") String token
    ){
        String actualUser = usuarioService.getUser(token);
        return ResponseEntity.ok(emeHabilidadService.getEme_habilidadById(id));
    }


    @PostMapping("")
    public ResponseEntity<?> postEmeHabilidad(
            @RequestBody Eme_HabilidadEntity eme,
            @RequestHeader(value = "Authorization",required = false) String token) {
        String actualUser= usuarioService.getUser(token);

        /*---*/


        Eme_HabilidadEntity eme_habilidad = emeHabilidadService.createEmeHabilidad(eme,actualUser);
        if (eme_habilidad!=null)
            return ResponseEntity.ok(eme_habilidad);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<Eme_HabilidadEntity> updateEmeHabilidad(
            @RequestBody Eme_HabilidadEntity eme_habilidad,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        Eme_HabilidadEntity updated = emeHabilidadService.updateEmeHabilidad(eme_habilidad,actualUser);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteEmeHabilidad(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        if(emeHabilidadService.deleteEmeHabilidad(id,actualUser)){ // TODO: Force delete? con bool como param
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }




}
